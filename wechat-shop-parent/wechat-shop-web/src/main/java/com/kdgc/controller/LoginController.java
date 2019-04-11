package com.kdgc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdgc.base.controller.BaseController;
import com.kdgc.constants.BaseApiConstants;
import com.kdgc.constants.Constants;
import com.kdgc.entity.UserEntity;
import com.kdgc.feign.UserFeign;
import com.kdgc.util.CookieUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

/**
 * @author Mr.Chen
 * @Classname LoginController
 * @Description 登陆controller
 * @Date 2019/4/2 21:19
 */
@Controller
public class LoginController extends BaseController {

    private static final String LOGIN = "login";

    private static final String INDEX = "index";

    private static final String ERROR = "error";

    private static final String ASSOCIATEDACCOUNT = "associatedAccount";

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/localLogin")
    public String localLogin(String source, HttpServletRequest request) {
        request.setAttribute("source", source);
        return LOGIN;
    }

    @RequestMapping("/login")
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response, String source,
            HttpSession session) {
        if (StringUtils.isNotEmpty(source) && source.equals(Constants.USER_SOURCE_QQ)) {
            String openId = (String) session.getAttribute(Constants.USER_SESSION_OPINID);
            userEntity.setOpenId(openId);
        }
        Map<String, Object> login = userFeign.login(userEntity);
        Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
            String msg = (String) login.get(BaseApiConstants.HTTP_200_NAME);
            return setError(request, msg, LOGIN);
        }
        // 登陆成功之后，获取token、将token存放在cookie中去
        String token = (String) login.get(BaseApiConstants.HTTP_DATA_NAME);
        CookieUtils.addCookie(response, Constants.USER_TOKEN, token, Constants.WEB_USER_TOKEN_TERMVALIDITY);
        return INDEX;
    }

    @RequestMapping("/authorizeUrl")
    public String authorizeUrl(HttpServletRequest request) throws QQConnectException {
        String authorizeUrl = new Oauth().getAuthorizeURL(request);
        return "redirect:" + authorizeUrl;
    }

    public String qqLoginCallback(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws QQConnectException {
        // 获取授权码
        AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
        // 获取sccess_token
        String accessToken = accessTokenObj.getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            return setError(request, "QQ授权失败", ERROR);
        }
        // 获取openId
        OpenID openIDObj = new OpenID(accessToken);
        String userOpenId = openIDObj.getUserOpenID();
        if (StringUtils.isEmpty(userOpenId)) {
            return setError(request, "QQ授权失败", ERROR);
        }
        // 数据库查找是否关联、如果没有关联、先跳转到关联页面。否则直接登陆
        Map<String, Object> stringObjectMap = userFeign.userLoginByOpenId(userOpenId);
        Integer code = (Integer) stringObjectMap.get(BaseApiConstants.HTTP_CODE_NAME);
        if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
            String token = (String) stringObjectMap.get(BaseApiConstants.HTTP_DATA_NAME);
            CookieUtils.addCookie(response, Constants.USER_TOKEN, token, Constants.WEB_USER_TOKEN_TERMVALIDITY);
            return "redirect:/" + INDEX;
        }
        // 没有关联QQ账户
        session.setAttribute(Constants.USER_SESSION_OPINID, userOpenId);
        return ASSOCIATEDACCOUNT;
    }
}
