package com.pug.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class);
    }

//    @LoadBalanced
//    该注释是用于负载均衡，访问时不能用ip要使用服务名，否则无法访问
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}