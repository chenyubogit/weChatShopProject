package com.kdgc.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kdgc.adapter.MessageAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname SMSMailboxService
 * @Description 发送邮件服务
 * @Date 2019/3/30 17:46
 */
@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter {

    @Override
    public void distribute(JSONObject jsonObject) {
        String mail = jsonObject.getString("mail");
        String userName = jsonObject.getString("userName");
        log.info("#######消费者收到消息··mail{},userName{}", mail, userName);
    }
}
