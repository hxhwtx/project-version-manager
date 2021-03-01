/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "用户code")
    private String code;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户头像, 图像的url")
    private String photo;

    @ApiModelProperty(value = "登录中文名")
    private String userName;

    @ApiModelProperty(value = "最后登陆ip")
    private String loginIp;

    @ApiModelProperty(value = "最后登陆时间")
    private Date loginDate;

    @ApiModelProperty(value = "到期时间（弃用）")
    private Date expiryTime;

    @ApiModelProperty(value = "1 主题1  2 主题2  3 主题3")
    private String themeType;

    @ApiModelProperty(value = "用户经纬度  经度,纬度")
    private String location;

    @ApiModelProperty(value = "缩放比例")
    private String scale;

    @ApiModelProperty(value = "租户Id")
    private String tenantId;

    @ApiModelProperty(value = "租户类型：0-租户（默认），1-租主, 2-超级管理员")
    private String tenantType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改者")
    private String updateUser;

    @ApiModelProperty(value = "删除标志，0-正常，1删除")
    private Boolean delFlag;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "激活状态, 1-启用， 0-禁用")
    private Integer activateFlag;

    @ApiModelProperty(value = "Admin设置, 内部是一个json")
    private String actionSetting;

    @ApiModelProperty(value = "是否锁定,1-是，0-否")
    private Integer lockingFlag;

    @ApiModelProperty(value = "是否是admin用户账号，1-是，0-否（默认）")
    private Integer adminUser;

    @ApiModelProperty(value = "修改密码标记")
    private String updatePdwFlag;


}
