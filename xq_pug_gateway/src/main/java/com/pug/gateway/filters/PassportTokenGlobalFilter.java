//package com.pug.gateway.filters;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class PassportTokenGlobalFilter implements GlobalFilter, Ordered {
//
//    // 模拟写死token的值，未来可能是从jwt和登录鉴权中获取，或者从redis获取
//    private final static String TOKEN_VALUE = "123456";
//    private final static String TOKEN_KEY = "token";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getHeaders().getFirst(TOKEN_KEY);
//        if (token == null) {
//            token = exchange.getRequest().getQueryParams().getFirst(TOKEN_KEY);
//        }
//        if (null != token) {
//            if (TOKEN_VALUE.equals(token)) {
//                return chain.filter(exchange);
//            }
//        }
//        byte[] bytes = ("sorry, you d'not browser [" + exchange.getRequest().getPath() + "]").getBytes(StandardCharsets.UTF_8);
//        DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(bytes);
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().writeWith(Flux.just(wrap));
//    }
//
//    @Override
//    public int getOrder() {
//        return 100;
//    }
//}
