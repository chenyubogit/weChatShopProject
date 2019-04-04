package com.kdgc.base.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.kdgc.constants.BaseApiConstants;
import com.kdgc.entity.UserEntity;
import com.kdgc.feign.UserFeign;

/**
 * @author Mr.Chen
 * @Classname BaseController
 * @Description 封装baseController
 * @Date 2019/3/31 18:52
 */
@Controller
public class BaseController {

    @Autowired
    private UserFeign userFeign;

    public UserEntity getUserEntity(String token) {
        Map<String, Object> userMap = userFeign.getUser(token);
        Integer code = (Integer) userMap.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
            return null;
        }
        // 获取data参数
        LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseApiConstants.HTTP_DATA_NAME);
        String json = JSON.toJSONString(linkedHashMap);
        UserEntity userEntity = JSON.parseObject(json, UserEntity.class);
        return userEntity;
    }

    /**
     * 封装错误信息给前台页面展示
     * 
     * @param request
     * @param msg
     * @param addres
     * @return
     */
    public String setError(HttpServletRequest request, String msg, String addres) {
        request.setAttribute("error", msg);
        return addres;
    }
}
