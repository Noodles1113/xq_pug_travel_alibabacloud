package com.pug.cloud.sentinel.liukong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 直接
@RestController
public class SentinelController {

    @GetMapping("/testA")
    public String testA(String a, String b) {
        return a + " " + b;
    }

    @GetMapping("/testB")
    public String testB(String a, String b) {
        return a + " " + b;
    }
}
