package com.pug.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
// 测试
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class);
    }
}
