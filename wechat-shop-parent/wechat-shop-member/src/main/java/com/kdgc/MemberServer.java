package com.kdgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Mr.Chen
 * @Classname MemberServer
 * @Description 启动
 * @Date 2019/3/27 17:43
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberServer {

    public static void main(String[] args) {
        SpringApplication.run(MemberServer.class, args);
    }
}
