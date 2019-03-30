package com.kdgc.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdgc.entity.UserEntity;

/**
 * @author Mr.Chen
 * @Classname UserService
 * @Description 用户服务
 * @Date 2019/3/30 11:33
 */
@RequestMapping("/member")
public interface UserService {

    /**
     * 注册服务
     * 
     * @RequestBody 传递JSON格式
     * @param userEntity
     * @return
     */
    @PostMapping("/regist")
    public Map<String, Object> regist(@RequestBody UserEntity userEntity);
}
