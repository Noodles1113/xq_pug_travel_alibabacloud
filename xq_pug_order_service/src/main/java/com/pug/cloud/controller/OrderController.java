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
        // 远程调用获取用服务
        return restTemplate.getForObject("http://localhost:8011/user/getUser", String.class);
//         return restTemplate.getForObject("http://xq_pug_user_service/user/getUser", String.class);
    }
}
