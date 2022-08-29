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

    @GetMapping("/getUser")
    public String getUser() {
//        return "success!!!";
        return uname;
    }

}