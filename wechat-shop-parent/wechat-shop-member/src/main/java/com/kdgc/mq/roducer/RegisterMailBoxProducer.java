package com.kdgc.mq.roducer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Chen
 * @Classname RegisterMailBoxProducer
 * @Description 往消息服务推送邮件信息
 * @Date 2019/3/30 17:13
 */
@Service
public class RegisterMailBoxProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(Destination destination, String json) {
        jmsMessagingTemplate.convertAndSend(destination, json);
    }
}
