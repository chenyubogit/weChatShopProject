package com.kdgc.manage;

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
}
