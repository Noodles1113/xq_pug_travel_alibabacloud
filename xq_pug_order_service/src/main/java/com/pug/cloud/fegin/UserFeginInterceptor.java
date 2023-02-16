package com.pug.cloud.fegin;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * 当某个服务在通过openfeign去调用远程服务接口的时候，就先会进入到拦截器中执行业务逻辑，在发起远程请求
 */
public class UserFeginInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("拦截器开启");
        // 1: 从订单请求中的header里获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 2：获取token
        String token = request.getHeader("token");
        // 3: 放入到openfegin的requestTemplate的请求头中
        requestTemplate.header("token", token);
        System.out.println("拦截器结束");
    }
}
