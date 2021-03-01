/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.service.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author weiller
 * @since 2021-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tenant对象", description="租户表")
public class TenantVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String accountName;

    private String tenantName;

    private Integer activateFlag;

    private String adminMail;

    private String actionSetting;

    private String logoUrl;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Boolean delFlag;

    private String tenantId;

    private String type;

    private Date dueTime;

    private Integer maxTenantNum;

    private String adminCallNumber;

    private String tenantSwitch;

    private String viewType;

    private String tenantKey;


}
