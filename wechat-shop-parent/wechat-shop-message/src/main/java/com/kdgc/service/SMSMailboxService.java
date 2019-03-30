package com.kdgc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public void distribute(JSONObject jsonObject) {
        String mail = jsonObject.getString("mail");
        String userName = jsonObject.getString("userName");
        log.info("#######消费者收到消息··mail{},userName{}", mail, userName);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setFrom(mailFrom);
        message.setSubject("恭喜你成为微信商城用户···");
        message.setText("恭喜" + userName + "今天成为了商城新用户，谢谢你的关心·");
        log.info("####发送短信邮箱mail{}" + mail);
        mailSender.send(message);
    }
}
