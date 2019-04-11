package com.kdgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Mr.Chen
 * @Classname MobileServer
 * @Description web服务启动类
 * @Date 2019/3/31 18:45
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MobileServer {

    public static void main(String[] args) {
        SpringApplication.run(MobileServer.class, args);
    }
}
