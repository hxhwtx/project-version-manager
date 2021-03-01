/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.controller;

import com.wpg.common.mybatis.config.model.PageQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpg.tools.common.module.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.wpg.projectversion.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.wpg.projectversion.model.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {
  @Autowired
  private IUserService  service;

  /**
  * @Description: 新增
  */
  @PostMapping("/add")
  public RestResult add(@RequestBody User obj){
    service.save(obj);
    return RestResult.success();
  }

 /**
 * @Description: 修改
 */
 @PostMapping("/edit")
 public RestResult edit( User obj){
   service.updateById(obj);
   return RestResult.success();
 }

 /**
 * @Description: 查看
 */
 @GetMapping("/get/{id}")
 public RestResult getById(@PathVariable("id") String id){
   User obj = service.getById(id);
   return RestResult.success(obj);
 }

 /**
 * @Description: 删除
 */
 @DeleteMapping("/delete/{id}")
 public RestResult deleteById(@PathVariable("id") String id){
   service.removeById(id);
   return RestResult.success();
 }

 /**
 * @Description: 分页查询
 */
 @PostMapping("/get/page")
 @ApiOperation("分页查询")
 public RestResult getPage(@RequestBody PageQuery pageQuery){
   Page<User> result = service.page(new Page<>(pageQuery.getCurrent(),pageQuery.getSize()));
   return RestResult.success(result);
 }
}
