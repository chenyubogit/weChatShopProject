package com.kdgc.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 登陆 1、登陆成功后将用户的userId存放在redis中、作为key，生成对应的token、作为value返还给客户端
     * 
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserEntity userEntity);

    /**
     * 使用token查询用户信息
     * 
     * @param token
     * @return
     */
    @PostMapping("/getUser")
    Map<String, Object> getUser(@RequestParam("token") String token);
}
