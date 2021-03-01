package com.wpg.projectversion.minio.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wpg.projectversion.minio.service.MinioService;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MinioService minioService;

    @RequestMapping("/")
    public String index(ModelMap map){
        List<JSONObject> data = new ArrayList<>();
        Iterable<Result<Item>> list = minioService.listObjects("test", null , null , 10);
        list.forEach( itemResult -> {
            try {
                Item item = itemResult.get();
                JSONObject d1 = JSONUtil.createObj();
                d1.set("objectName" , item.objectName());
                d1.set("size" , item.size());
                d1.set("isDir" , item.isDir());//是否目录
                data.add(d1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        map.addAttribute("list" , data);
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/jsupload")
    public String jsupload(){
        return "jsupload";
    }

}
