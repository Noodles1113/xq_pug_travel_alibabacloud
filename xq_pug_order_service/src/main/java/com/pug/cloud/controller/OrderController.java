package com.pug.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/makeOrder")
    public String makeOrder() {
        // 远程调用获取用服务
        String serverResponse = restTemplate.getForObject("http://localhost:8011/getUser", String.class);
        return serverResponse;
    }
}