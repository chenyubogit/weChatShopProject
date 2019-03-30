package com.kdgc.mq;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kdgc.adapter.MessageAdapter;
import com.kdgc.constants.MqInterfaceType;
import com.kdgc.service.SMSMailboxService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname ConsumerDistribute
 * @Description 消费消息 mq从对类中获取最新消息
 * @Date 2019/3/30 17:47
 */
@Component
@Slf4j
public class ConsumerDistribute {

    @Autowired
    private SMSMailboxService smsMailboxService;

    @JmsListener(destination = "messages.queue")
    public void distribute(String json) {
        log.info("#####收到消息、消息内容：json{}:" + json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject header = jsonObject.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
        // 发送邮件
        case MqInterfaceType.SMS_MAIL:
            messageAdapter = smsMailboxService;
            break;
        default:
            break;
        }
        JSONObject content = jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}
