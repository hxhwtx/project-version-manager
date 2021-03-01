package com.wpg.projectversion.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpg.projectversion.model.Tenant;
import com.wpg.projectversion.service.IBizTenantService;
import com.wpg.projectversion.service.ITenantService;
import com.wpg.tools.redis.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BizTenantServiceImpl implements IBizTenantService {
    //注入 对应的服务
    @Autowired
    private ITenantService service;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Page<Tenant> getPage(Tenant tenant) {
        // 构建分页
        Page<Tenant> tenantPage = new Page<>(1L, 10L);
        // 构建查询条件
        LambdaQueryWrapper<Tenant> tenantLambdaQueryWrapper = Wrappers.<Tenant>lambdaQuery();
        tenantLambdaQueryWrapper.likeRight(Tenant::getTenantName,"超级");
        // 查询结果
        Page<Tenant> result = service.page(tenantPage,tenantLambdaQueryWrapper);
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void testa() {
        Tenant tenant = new Tenant();
        tenant.setId("hufei");
        tenant.setAccountName("hufei");
        service.save(tenant);
        tenant.setAccountName("hufei1");
        boolean result = service.updateById(tenant);
    }

    private static final String TASK_LOCKKEY="redisLockTest";
    private static final String TENANT_OVERDUE_PRFFIX="aaa";
    @Override
    @RedisLock(value = TASK_LOCKKEY, valueSuffix = TENANT_OVERDUE_PRFFIX, waitTime = 1, leaseTime = 1)
    public void lockRedisTest() {
        RLock anyLock = redissonClient.getLock("anyLock");
        // 加锁
//        anyLock.lock(1,TimeUnit.SECONDS);
        // 获取值
        System.out.println("进入方法------线程id"+Thread.currentThread().getId());
        // 未解锁 前去拿 锁
        RLock anyLock1 = redissonClient.getLock(TASK_LOCKKEY+TENANT_OVERDUE_PRFFIX);
        boolean b = anyLock1.tryLock();
        if(b){
            anyLock1.lock(10,TimeUnit.SECONDS);
        }

    }
}
