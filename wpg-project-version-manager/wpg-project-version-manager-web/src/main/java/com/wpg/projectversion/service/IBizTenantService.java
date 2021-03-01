package com.wpg.projectversion.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpg.projectversion.model.Tenant;

/**
 * @author hufei
 * @Description: Tenant 逻辑 服务
 * @date 2021/2/3 11:14
 */
public interface IBizTenantService {

    Page<Tenant> getPage(Tenant tenant);

    void testa();

    // 分布式锁
    void lockRedisTest();
}
