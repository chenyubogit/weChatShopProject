package com.kdgc.token;

import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * @author Mr.Chen
 * @Classname TokenUtils
 * @Description 生成token的工具类
 * @Date 2019/3/31 10:50
 */
@Component
public class TokenUtils {

    public String getToken() {
        return UUID.randomUUID().toString();
    }
}
