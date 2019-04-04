package com.kdgc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdgc.base.controller.BaseController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname UserController
 * @Description TODO
 * @Date 2019/3/31 18:41
 */
@Slf4j
@Controller
public class UserController extends BaseController {

    public static final String INDEX = "index";

    @RequestMapping("/index")
    public String index(HttpServletRequest request, String token) {
        log.info("我的web项目搭建成功了,userName{}:" + getUserEntity(token).getUserName());
        request.setAttribute("userName", "");
        return INDEX;
    }
    // public Map<String, Object>
}
