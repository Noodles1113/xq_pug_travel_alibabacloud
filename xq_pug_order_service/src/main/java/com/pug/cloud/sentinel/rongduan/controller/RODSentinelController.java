package com.pug.cloud.sentinel.rongduan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RODSentinelController {

    @GetMapping("/testC")
    public String testC(Boolean isSlow) {
        if(isSlow) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "慢调用比例熔断";
    }

    @GetMapping("/testD")
    public String testD(Boolean isException) {
        if(isException) {
            throw new RuntimeException("request exception");
        }
        return "异常比例/数熔断";
    }
}
