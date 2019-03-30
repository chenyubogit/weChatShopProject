package com.kdgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Mr.Chen
 * @Classname MessageServer
 * @Description MQ消费消息
 * @Date 2019/3/30 18:05
 */
@SpringBootApplication
@EnableEurekaClient
public class MessageServer {

    public static void main(String[] args) {
        SpringApplication.run(MessageServer.class, args);
    }
}
