/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.service.impl;

import com.wpg.projectversion.model.User;
import com.wpg.projectversion.mapper.UserMapper;
import com.wpg.projectversion.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
