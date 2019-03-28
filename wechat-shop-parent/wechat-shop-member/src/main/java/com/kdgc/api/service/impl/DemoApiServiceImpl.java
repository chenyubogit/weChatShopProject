package com.kdgc.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.kdgc.api.service.DemoApiService;
import com.kdgc.common.api.BaseApiService;
import com.kdgc.common.redis.BaseRedisService;

/**
 * @author Mr.Chen
 * @Classname DemoServiceImpl
 * @Description 测试服务实现类
 * @Date 2019/3/27 17:38
 */
@RestController
public class DemoApiServiceImpl extends BaseApiService implements DemoApiService {

    @Autowired
    private BaseRedisService redisService;

    @Override
    public Map<String, Object> demo() {
        return setResultSucc();
    }

    @Override
    public Map<String, Object> setKey(String key, String value) {
        redisService.setString(key, value);
        return setResultSucc();
    }

    @Override
    public Map<String, Object> getKey(String key) {
        String value = redisService.get(key);
        return setResultSuccData(value);
    }

    @Override
    public Map<String, Object> delKey(String key) {
        redisService.del(key);
        return setResultSucc();
    }
}
