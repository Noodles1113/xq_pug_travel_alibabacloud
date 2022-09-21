package com.pug.cloud.sentinel.liukong.redian.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class REDSentinerlController {

    // http://localhost:8012/testHotKey?hot1=1&hot2=2&hot3=3
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "blockHandler")
    public String testA(String hot1, String hot2, String hot3) {
        return hot1 + " " + hot2 + " " + hot3;
    }

    // 处理异常方法，方法签名要和对应的接口方法保持一致
    public String blockHandler(String hot1, String hot2, String hot3, BlockException exception) {
        return "热点限流稍后重试";
    }

}
