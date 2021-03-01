/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.service;

import com.wpg.projectversion.model.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
public interface ITenantService extends IService<Tenant> {
    /**
     * @author hufei
     * @Description: 关联查询
     * @date 2021/2/3 11:28
     */
    List<Tenant> getTenantByRelation();
}
