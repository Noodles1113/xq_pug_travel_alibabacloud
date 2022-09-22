package com.pug.cloud.fegin;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class UserFeginIntercetor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("拦截器开启");
    }
}
