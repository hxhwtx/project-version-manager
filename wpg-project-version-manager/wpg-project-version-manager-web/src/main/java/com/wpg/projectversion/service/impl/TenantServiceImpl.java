/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.wpg.projectversion.service.impl;

import com.wpg.projectversion.model.Tenant;
import com.wpg.projectversion.mapper.TenantMapper;
import com.wpg.projectversion.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author weiller
 * @since 2021-02-03
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    /**
     * @author hufei
     * @Description: 关联查询
     * @date 2021/2/3 11:28
     */
    @Override
    public List<Tenant> getTenantByRelation() {
        return baseMapper.getTenantByRelation();
    }
}
