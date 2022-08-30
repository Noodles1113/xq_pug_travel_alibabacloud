package com.pug.cloud.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
// 配置文件实时刷新
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Value("${uname}")
    private String uname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/getUser")
    public String getUser() {
        return uname;
    }

    @GetMapping("/getUser2")
    public String getUser2() {
        return "port: " + port;
    }

}