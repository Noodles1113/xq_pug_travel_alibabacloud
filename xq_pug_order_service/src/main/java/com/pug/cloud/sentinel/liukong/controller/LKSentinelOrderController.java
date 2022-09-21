package com.pug.cloud.sentinel.liukong.controller;

import com.pug.cloud.sentinel.liukong.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// query1关联
// query2链路
@RestController
public class LKSentinelOrderController {

    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/order/query1")
    public String orderQuery1() {
        return "order query1";
    }

    @GetMapping("/order/update1")
    public String orderUpdate1() {
        return "order update1";
    }

    @GetMapping("/order/query2")
    public String orderQuery2() {
        return sentinelService.queryGoods("query2");
    }

    @GetMapping("/order/update2")
    public String orderUpdate2() {
        return sentinelService.queryGoods("update2");
    }
}
