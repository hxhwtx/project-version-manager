package com.wpg.projectversion.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wpg.projectversion.model.Tenant;
import com.wpg.tools.common.module.RestResult;
import com.wpg.tools.redis.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    private static final String REDIS_TEST_KEY ="redis_test_key";
    private static final String REDIS_TEST_OBJECT_KEY ="redis_test_object_key";
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @author hufei
     * @Description: redis 操作
     * @date 2021/1/27 10:20
     */
    @ApiOperation("redis测试")
    @GetMapping("/test")
    public RestResult RedisWork(){
        //  字符操作
        boolean setResult = redisUtil.set(REDIS_TEST_KEY, "hufei");
        log.info("setResult"+setResult);
        String getResult = (String)redisUtil.get(REDIS_TEST_KEY);
        log.info("getResult"+getResult);
        // 对象操作
        Tenant tenant = new Tenant();
        tenant.setId("aaaaa");
        redisUtil.set(REDIS_TEST_OBJECT_KEY,JSON.toJSONString(tenant),1000);
        Object byKey = redisUtil.getByKey(REDIS_TEST_OBJECT_KEY);
        log.info("byKey"+ JSON.toJSONString(byKey));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           log.error("aaaa");
        }
        Object byKey2 =  redisUtil.get(REDIS_TEST_OBJECT_KEY);
        log.info("byKey2"+ JSON.toJSONString(byKey2));
        //普通缓存放入并设置时间
        return RestResult.success();
    }

    @ApiOperation("指定缓存失效时间")
    @GetMapping("/test1")
    public RestResult RedisWork1(){
        String key =REDIS_TEST_KEY+"1";
        //  字符操作
        boolean setResult = redisUtil.set(key, "hufei");
        log.info("setResult:"+setResult);
        redisUtil.expire(key,6);
        //获取过期时间
        long expire = redisUtil.getExpire(key);
        log.info("expire:"+expire);
        if(redisUtil.hasKey(key)){
            return RestResult.success(expire);
        }
        return RestResult.fail("key失效");
    }


    @ApiOperation("删除缓存")
    @GetMapping("/test2")
    public RestResult RedisWork2(){
        //  字符操作
        boolean setResult = redisUtil.set(REDIS_TEST_KEY+"2", "hufei");
        log.info("setResult:"+setResult);
        boolean setResult2 = redisUtil.set(REDIS_TEST_KEY+"21", "hufei");
        log.info("setResult2:"+setResult2);
        if(redisUtil.hasKey(REDIS_TEST_KEY+"2") && redisUtil.hasKey(REDIS_TEST_KEY+"21")){
            log.info("两个key存在");
        }
//        redisUtil.del(REDIS_TEST_KEY+"2",REDIS_TEST_KEY+"21");
        redisUtil.delByPattern(REDIS_TEST_KEY+"2*");
        if(!redisUtil.hasKey(REDIS_TEST_KEY+"2") && !redisUtil.hasKey(REDIS_TEST_KEY+"21")){
            log.info("两个key不存在");
        }
        return RestResult.success();
    }


    @ApiOperation("递增缓存")
    @GetMapping("/test3")
    public RestResult RedisWork3(){
        boolean setResult = redisUtil.set(REDIS_TEST_KEY, "10");
        log.info("setResult:"+setResult);
        long incr = redisUtil.incr(REDIS_TEST_KEY, 2);
        log.info("incr:"+incr);
        String o = (String)redisUtil.get(REDIS_TEST_KEY);
        log.info("o:"+o);
        return RestResult.success();
    }


    @ApiOperation("hash操作缓存")
    @GetMapping("/test4")
    public RestResult RedisWork4(){
        String key =REDIS_TEST_KEY + "map";
        Map<String, Object> map = Maps.newHashMap();
        map.put("hufei","hufeiData");
        // 放入map
        boolean hmset = redisUtil.hmset(key, map);
        // 获取map
        Map<Object, Object> maps = redisUtil.hmget(key);
        // 根据map的key 获取value
        String hufei = (String)redisUtil.hget(key, "hufei");
        redisUtil.hset(key,"tanyu","tanyuData");
        String tanyu = (String)redisUtil.hget(key, "tanyu");
        // 删除
        redisUtil.hdel(key,"tanyu");
        String tanyu1 = (String)redisUtil.hget(key, "tanyu");
        return RestResult.success();
    }


    @ApiOperation("集合操作缓存")
    @GetMapping("/test5")
    public RestResult RedisWork5(){
        String key =REDIS_TEST_KEY + "list";
        // 放入4个值
        boolean b = redisUtil.lSet(key, "hufei");
        log.info("b:"+b);
        boolean b1 = redisUtil.lSet(key, "yanyu");
        log.info("b1:"+b1);
        boolean b2 = redisUtil.lSet(key, "hufei");
        log.info("b2:"+b2);
        boolean b3 = redisUtil.lSet(key, "hufei");
        log.info("b3:"+b3);
        long size = redisUtil.lGetListSize(key);
        log.info("size:"+size);
        // 获取前两
        List<Object> aaa = redisUtil.lGet( key, 0L, 1L);
        log.info("aaa:"+JSON.toJSONString(aaa));
        // 更新第四个
        redisUtil.lUpdateIndex(key,3,"王伟");
        // 获取全部
        aaa = redisUtil.lGet( key, 0L, 3L);
        log.info("aaa:"+JSON.toJSONString(aaa));
        return RestResult.success();
    }



}
