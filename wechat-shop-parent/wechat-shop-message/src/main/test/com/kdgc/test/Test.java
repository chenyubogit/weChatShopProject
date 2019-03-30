package com.kdgc.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mr.Chen
 * @Classname Test
 * @Description 单元测试类
 * @Date 2019/3/30 22:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @org.junit.Test
    public void test() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("测试通知");
        message.setText("今晚测试通知");
        message.setTo("326184115@qq.com");
        message.setFrom(mailFrom);
        // System.out.println(mailFrom);
        mailSender.send(message);
    }
}
