package com.wpg.projectversion.minio.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wpg.projectversion.minio.conf.FileModel;
import com.wpg.projectversion.minio.conf.MinioConfig;
import com.wpg.projectversion.minio.service.MinioService;
import io.minio.messages.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private MinioService minioService;

    @Resource
    private MinioConfig minioConfig;

    /**
     * 表单/JS 上传文件
     *
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile file) {
        JSONObject result = JSONUtil.createObj();
        result.set("code", "fail");
        try {
            if (file != null && !file.isEmpty()) {
                Map<String, String> map = minioService.putObject(minioConfig.getBucketDefaultName(), file);
                System.out.println(map);
                if (map != null) {
                    result.set("code", "success");
                    result.set("message", "上传成功");
                    result.set(FileModel.url, map.get(FileModel.url));
                    result.set(FileModel.name, map.get(FileModel.name));
                    result.set(FileModel.oldName, Base64.decodeStr(map.get(FileModel.oldName), "UTF-8"));
                    result.set(FileModel.size, map.get(FileModel.size));
                    result.set(FileModel.uploadDate, map.get(FileModel.uploadDate));
                    result.set(FileModel.suffix, map.get(FileModel.suffix));
                    map = null;
                } else {
                    result.set("message", "上传失败，请重新上传");
                }
            } else {
                result.set("message", "请传入文件");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            result.set("message", ex.getMessage());
        }
        return result;
    }


    /**
     * 预览文件（只能预览图片、txt等部分文件）
     * objectName = dateDir +"/"+fileName;
     * @param bucketName 桶名称
     * @param dateDir    时间目录
     * @param fileName   文件名
     * @return
     */
    @GetMapping("/view/{bucketName}/{dateDir}/{fileName}")
    public ResponseEntity<Object> view(@PathVariable String bucketName, @PathVariable String dateDir, @PathVariable String fileName) {
        if (bucketName == null || fileName == null || dateDir == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass in the correct parameters");
        }
        String objectName = dateDir +"/"+fileName;
        Tags tags = minioService.getObjectTags(bucketName , objectName);
        if(tags != null && tags.get() != null){
            try {
                Map<String, String> maps = tags.get();
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=" + maps.get(FileModel.name))
                        .header(HttpHeaders.CONTENT_TYPE, maps.get(FileModel.contentType))
                        .header(HttpHeaders.CONTENT_LENGTH, maps.get(FileModel.size))
                        .header("Connection", "close")
                        .body(IoUtil.readBytes(minioService.getObject(bucketName , objectName)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File Not Found");
    }

    /**
     * 下载附件
     * objectName = dateDir +"/"+fileName;
     * @param bucketName 桶名称
     * @param dateDir    时间目录
     * @param fileName   文件名
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download/{bucketName}/{dateDir}/{fileName}")
    public void downloadFile(@PathVariable String bucketName, @PathVariable String dateDir, @PathVariable String fileName , HttpServletResponse response){
        if (bucketName == null || fileName == null || dateDir == null) {
            return ;
        }
        String objectName = dateDir +"/"+fileName;
        minioService.downloadObject(bucketName , objectName , response);
        /*
        ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + maps.get(FileModel.name))
            .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
            .header(HttpHeaders.CONTENT_LENGTH, maps.get(FileModel.size))
            .header("Connection", "close")
            .body(IoUtil.readBytes(minioService.getObject(bucketName , objectName)));
        * */
    }

    /**
     * 删除附件
     * objectName = dateDir +"/"+fileName;
     * @param bucketName 桶名称
     * @param dateDir    时间目录
     * @param fileName   文件名
     * @return
     */
    @GetMapping("/delete/{bucketName}/{dateDir}/{fileName}")
    public JSONObject deleteFile(@PathVariable String bucketName, @PathVariable String dateDir, @PathVariable String fileName) {
        JSONObject result = JSONUtil.createObj();
        result.set("code", "fail");
        if (bucketName == null || fileName == null || dateDir == null) {
            result.set("message", "请传入正确参数");
            return result;
        }
        String objectName = dateDir +"/"+fileName;
        boolean flag = minioService.removeObject(bucketName, objectName);
        result.set("code", flag ? "success" : "fail");
        result.set("message", flag ? "删除成功" : "删除失败");
        return result;
    }
}
