/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.wpg.projectversion.controller;

import com.wpg.common.mybatis.config.model.PageQuery;
import com.wpg.projectversion.service.IBizTenantService;
import com.wpg.tools.web.aop.hasrole.HasRoleAnnotation;
import com.wpg.tools.web.bo.UserBaseDTO;
import com.wpg.usercenter.model.bo.tenant.TenantVO;
import com.wpg.web.util.UserContextHolder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpg.tools.common.module.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.wpg.projectversion.service.ITenantService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.wpg.projectversion.model.Tenant;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/rest/tenant")
@Slf4j
@HasRoleAnnotation(productCode="module_common",menuOrButtonCode="exportProduct")
public class TenantController {
    @Autowired
    private ITenantService service;

    @Autowired
    private IBizTenantService iBizTenantService;

    @Value("${spring.application.name}")
    private String appName;

   /* @PostMapping("/testfile")
    @ApiOperation("文件上传")
    public RestResult testfile(@RequestParam("file") MultipartFile file) {
//        DataFileDto dataFileDto = new DataFileDto();
//        dataFileDto.setFileContent(file);
//        dataFileDto.setFileName(file.getOriginalFilename());
//        dataFileDto.setRandomId(RandomUtil.randomString(5));
//        dataFileDto.setTenantId("001");
        RestResult restResult = remoteFileService.uploadFile(file, appName);
        return restResult;
    }
*/
    /**
     * @Description: 新增
     */
    @PostMapping("/add")
    public RestResult add(@RequestBody Tenant obj) {
        // 保存数据
        service.save(obj);
        return RestResult.success();
    }

    /**
     * @Description: 修改
     */
    @PostMapping("/edit")
    public RestResult edit(Tenant obj) {
        // 根据id 更新数据
        service.updateById(obj);
        return RestResult.success();
    }

    /**
     * @Description: Auth
     */
    @GetMapping("/get/{id}")
    // 操作日志
//    @AuditLog(operation = "'啊啊啊: ' + {#id}", module = LogOperationConstants.LOGIN, type = BaseLogTypeConstants.LOGIN)
//    @ApiOperation("Auth")
    // 内部接口 加上后 前端网页无法调用
//    @Inner
    // 加上后 可以通过UserContextHolder  获取用户信息
//    @Auth
    //  方法上权限校验 优先级高于  class类上的
//    @HasRoleAnnotation(productCode="module_common",menuOrButtonCode="exportProduct")
    public RestResult getById(@PathVariable("id") String id) {
        UserBaseDTO userBaseDTO = UserContextHolder.getUserBaseDTO();
        log.info("用户信息：-------------"+userBaseDTO.getId());
        // 根据id获取数据
        Tenant obj = service.getById(id);
        TenantVO tenantVO = new TenantVO();
        BeanUtils.copyProperties(obj,tenantVO);
        return RestResult.success(tenantVO);
    }

    /**
     * @Description: 删除
     */
    @DeleteMapping("/delete/{id}")
    public RestResult deleteById(@PathVariable("id") String id) {
        // 删除数据
        service.removeById(id);
        return RestResult.success();
    }

    /**
     * @Description: 分页查询
     */
    @PostMapping("/get/page")
    @ApiOperation("分页查询")
    public RestResult getPage(@RequestBody PageQuery pageQuery) {
        // 分页查询
        Page<Tenant> result = service.page(new Page<>(pageQuery.getCurrent(), pageQuery.getSize()));
        return RestResult.success(result);
    }

    @PostMapping("/get/biz/page")
    @ApiOperation("分页查询1")
    public RestResult getBizPage(@RequestBody PageQuery pageQuery) {
        // 分页查询
        Page<Tenant> result = iBizTenantService.getPage(new Tenant());
        return RestResult.success(result);
    }

    @PostMapping("/get/test")
    @ApiOperation("mybatis自己写sql")
    public RestResult test(@RequestBody PageQuery pageQuery) {
        // 分页查询
        List<Tenant> tenantByRelation = service.getTenantByRelation();
        return RestResult.success(tenantByRelation);
    }

    @PostMapping("/get/testa")
    @ApiOperation("事务测试")
    public RestResult testa(@RequestBody PageQuery pageQuery) {
        // 分页查询
        iBizTenantService.testa();
        return RestResult.success();
    }

    @ApiOperation("分布式锁测试")
    @GetMapping("/testLock")
    public RestResult testLock(){
        // 100 个线程测试
//        for(int i=0;i<100;i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("线程id"+Thread.currentThread().getId());
//                    iBizTenantService.lockRedisTest();
//                }
//            }).start();
//        }
        iBizTenantService.lockRedisTest();
        return RestResult.success();
    }
}
