package com.pug.cloud.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelService {

    @SentinelResource(value = "common")
    public String queryGoods(String action) {
        return action + ":query goods";
    }

}
