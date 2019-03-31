package com.kdgc.common.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Chen
 * @Classname BaseRedisService
 * @Description 封装redis
 * @Date 2019/3/27 20:50
 */
@Component
public class BaseRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加redis信息
     *
     * @param key
     * @param value
     * @param timeout
     */
    public void setString(String key, String value, Long timeout) {
        set(key, value, timeout);
    }

    public void setString(String key, String value) {
        set(key, value, null);
    }

    public void set(String key, Object value, Long timeout) {
        if (value != null) {
            if (value instanceof String) {
                String strValue = (String) value;
                stringRedisTemplate.opsForValue().set(key, strValue);
            }
            if (value instanceof Long) {
                String longValue = String.valueOf(value);
                stringRedisTemplate.opsForValue().set(key, longValue);
            }
            // 设置有效期
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 使用key查找redis信息
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 使用key删除redis信息
     *
     * @param key
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
}
