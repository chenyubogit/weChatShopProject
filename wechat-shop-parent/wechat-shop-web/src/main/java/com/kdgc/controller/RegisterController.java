package com.kdgc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdgc.base.controller.BaseController;
import com.kdgc.constants.BaseApiConstants;
import com.kdgc.entity.UserEntity;
import com.kdgc.feign.UserFeign;

/**
 * @author Mr.Chen
 * @Classname RegistController
 * @Description 注册controller
 * @Date 2019/3/31 19:43
 */
@Controller
public class RegisterController extends BaseController {

    private static final String LOCAL_REGISTER = "localRegister";

    private static final String LOGIN = "login";

    @Autowired
    public UserFeign uerFeign;

    @RequestMapping("/localRegister")
    public String localRegister() {
        return LOCAL_REGISTER;
    }

    @RequestMapping("/register")
    public String register(UserEntity userEntity, HttpServletRequest request) {
        try {
            Map<String, Object> registMap = uerFeign.regist(userEntity);
            Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_CODE_NAME);
            if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
                String msg = (String) registMap.get(BaseApiConstants.HTTP_200_NAME);
                return setError(request, msg, LOCAL_REGISTER);
            }
            // 注册成功
            return LOGIN;
        } catch (Exception e) {
            return setError(request, "注册失败", LOCAL_REGISTER);
        }
    }
}
