package com.kdgc.constants;

/**
 * @author Mr.Chen
 * @Classname Constants
 * @Description 定义项目所需常量
 * @Date 2019/3/31 10:57
 */
public interface Constants {

    /**
     * 用户生成token保存90天
     */
    Long USER_TOKEN_TERMVALIDITY = 60 * 60 * 24 * 90L;

    Integer WEB_USER_TOKEN_TERMVALIDITY = 100 * 60 * 60 * 24 * 90;

    String USER_TOKEN = "token";

    String USER_SESSION_OPINID = "opinId";

    String USER_SOURCE_QQ = "qq";
}
