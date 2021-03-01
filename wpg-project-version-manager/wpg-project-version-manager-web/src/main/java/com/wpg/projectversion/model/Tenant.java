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
 * 租户表
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tenant对象", description="租户表")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "账户名（登录名）")
    private String accountName;

    @ApiModelProperty(value = "租户名")
    private String tenantName;

    @ApiModelProperty(value = "激活状态, 1-激活， 0-未激活")
    private Integer activateFlag;

    @ApiModelProperty(value = "admin邮箱地址")
    private String adminMail;

    @ApiModelProperty(value = "Admin设置, 内部是一个json")
    private String actionSetting;

    @ApiModelProperty(value = "logo url")
    private String logoUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改者")
    private String updateUser;

    @ApiModelProperty(value = "删除标志, 0-正常，1删除")
    private Boolean delFlag;

    @ApiModelProperty(value = "租户id")
    private String tenantId;

    @ApiModelProperty(value = "租户类型：0-租户（默认），1-租主, 2-超级管理员")
    private String type;

    @ApiModelProperty(value = "到期时间")
    private Date dueTime;

    @ApiModelProperty(value = "租主创建最多的租户数")
    private Integer maxTenantNum;

    @ApiModelProperty(value = "admin手机号")
    private String adminCallNumber;

    @ApiModelProperty(value = "租主/户开关（1-开启：能管理下级用户信息；0-关闭：不能）")
    private String tenantSwitch;

    @ApiModelProperty(value = "页面显示形式")
    private String viewType;

    @ApiModelProperty(value = "租户key")
    private String tenantKey;


}
