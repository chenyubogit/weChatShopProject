package com.kdgc.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kdgc.api.service.DemoApiService;

/**
 * @author Mr.Chen
 * @Classname DemoServiceImpl
 * @Description 测试服务实现类
 * @Date 2019/3/27 17:38
 */
@RestController
public class DemoApiServiceImpl implements DemoApiService {

    @Override
    public Map<String, Object> demo() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("msg", "succ");
        return result;
    }
}
