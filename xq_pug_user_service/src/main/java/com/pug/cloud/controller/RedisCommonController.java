package com.pug.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RedisCommonController {

    @Value("${spring.redis.ip}")
    private String ip;
    @Value("${spring.redis.port}")
    private Integer port;

    @GetMapping("/config/redis")
    public String getCommon() {
        return "redis common : " + ip + " : " + port;
    }
}
