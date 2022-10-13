package com.pug.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/makeOrder")
    public String makeOrder() {
        // 远程调用获取用服务1
        // restTemplate没有加入注解@LoadBalanced时通过ip+port+api的方式调用其他服务
        return restTemplate.getForObject("http://localhost:8011/user/getUser", String.class);
    }

    @GetMapping("/makeOrder2")
    public String makeOrder2() {
        // 远程调用获取用服务2
        // restTemplate加入注解@LoadBalanced时可以通过服务名进行调用同时支持负载均衡
         return restTemplate.getForObject("http://xq-pug-user-service/user/getUser2", String.class);
    }
}
