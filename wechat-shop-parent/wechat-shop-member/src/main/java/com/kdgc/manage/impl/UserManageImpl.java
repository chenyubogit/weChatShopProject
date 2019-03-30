package com.kdgc.manage.impl;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kdgc.constants.DbTableName;
import com.kdgc.constants.MqInterfaceType;
import com.kdgc.dao.UserDao;
import com.kdgc.entity.UserEntity;
import com.kdgc.manage.UserManage;
import com.kdgc.mq.roducer.RegisterMailBoxProducer;
import com.kdgc.util.DateUtils;
import com.kdgc.util.MD5Util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname UserManageImpl
 * @Description UserManageImpl实现类
 * @Date 2019/3/30 11:40
 */
@Service
@Slf4j
public class UserManageImpl implements UserManage {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterMailBoxProducer registerMailBoxProducer;

    @Value("messages.queue")
    private String MESSAGE_QUEUE;

    @Override
    public void regist(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        userEntity.setPassword(md5PassWordSalt(userEntity.getPhone(), userEntity.getPassword()));
        userDao.save(userEntity, DbTableName.TABLE_MB_USER);
        // 队列
        Destination destination = new ActiveMQQueue(MESSAGE_QUEUE);
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
        log.info("####注册发送邮件报文，报文内容：" + mailMessage);
        registerMailBoxProducer.send(destination, mailMessage);
    }

    private String mailMessage(String email, String userName) {
        // 组装报文格式
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MqInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }

    @Override
    public String md5PassWordSalt(String phone, String password) {
        return MD5Util.MD5(phone + password);
    }
}
