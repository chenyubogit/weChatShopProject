package com.kdgc.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.Chen
 * @Classname DemoApiService
 * @Description 测试服务类
 * @Date 2019/3/27 17:34
 */
@RequestMapping("/demo")
public interface DemoApiService {

    /**
     * 服务demo
     * 
     * @return
     */
    @GetMapping("/demo")
    public Map<String, Object> demo();
}
