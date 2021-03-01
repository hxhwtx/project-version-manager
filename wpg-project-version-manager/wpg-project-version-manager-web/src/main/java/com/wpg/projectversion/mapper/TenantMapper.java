/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.mapper;

import com.wpg.projectversion.model.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
public interface TenantMapper extends BaseMapper<Tenant> {
    /**
     * @author hufei
     * @Description: 关联查询
     * @date 2021/2/3 11:28
     */
    List<Tenant> getTenantByRelation();
}
