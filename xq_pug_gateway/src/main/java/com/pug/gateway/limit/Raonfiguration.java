//package com.pug.gateway.limit;
//
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class Raonfiguration {
//
//    @Bean
//    public KeyResolver pathKeyResolver() {
//        return exchange -> Mono.just(
//                exchange.getRequest()
//                        .getPath()
//                        .toString()
//        );
//    }
//
//    @Bean
//    @Primary
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostString());
//    }
//
//
//    @Bean
//    public KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//    }
//
//
//    @Bean
//    public KeyResolver apiKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getPath().value());
//    }
//}
