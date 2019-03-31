package com.kdgc.api.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdgc.api.service.UserService;
import com.kdgc.common.api.BaseApiService;
import com.kdgc.entity.UserEntity;
import com.kdgc.manage.UserManage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname UserServiceImpl
 * @Description user服务的实现类
 * @Date 2019/3/30 11:35
 */
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService {

    @Autowired
    private UserManage userManage;

    @Override
    public Map<String, Object> regist(@RequestBody UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getUserName())) {
            return setParamsError("用户名不能为空");
        }
        if (StringUtils.isEmpty(userEntity.getPassword())) {
            return setParamsError("密码不能为空");
        }
        try {
            userManage.regist(userEntity);
            return setResultSucc();
        } catch (Exception e) {
            log.error("#### regist() ERROR:", e);
            return setResultError("注册失败");
        }
    }

    @Override
    public Map<String, Object> login(@RequestBody UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getPhone())) {
            return setParamsError("手机号不能为空");
        }
        if (StringUtils.isEmpty(userEntity.getPassword())) {
            return setParamsError("密码不能为空");
        }
        return userManage.login(userEntity);
    }

    @Override
    public Map<String, Object> getUser(@RequestParam("token") String token) {
        if (StringUtils.isEmpty(token)) {
            return setParamsError("token不能为空");
        }
        return userManage.getUser(token);
    }
}
