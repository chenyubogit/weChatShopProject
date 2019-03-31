package com.kdgc.manage;

import java.util.Map;

import com.kdgc.entity.UserEntity;

/**
 * @author Mr.Chen
 * @Classname UserManage
 * @Description user管理
 * @Date 2019/3/30 11:39
 */
public interface UserManage {

    /**
     * 注册服务
     *
     * @param userEntity
     */
    public void regist(UserEntity userEntity);

    /**
     * md5加密、加盐
     *
     * @param phone
     * @param password
     * @return
     */
    public String md5PassWordSalt(String phone, String password);

    /**
     * 用户登陆
     *
     * @param userEntity
     */
    Map<String, Object> login(UserEntity userEntity);

    /**
     * 通过token查询用户信息
     *
     * @param token
     * @return
     */
    Map<String, Object> getUser(String token);
}
