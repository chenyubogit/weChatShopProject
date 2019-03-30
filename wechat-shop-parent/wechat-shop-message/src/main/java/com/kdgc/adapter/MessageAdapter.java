package com.kdgc.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Mr.Chen
 * @Classname MessageAdapter
 * @Description 所有消息都会交给此类进行转发
 * @Date 2019/3/30 17:44
 */
public interface MessageAdapter {

    public void distribute(JSONObject jsonObject);
}
