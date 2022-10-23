package com.pug.gateway.predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class MyMethodRoutePredicateFactory extends AbstractRoutePredicateFactory<MyMethodRoutePredicateFactory.Config> {

    public static final String[] KEY_ARRAY = {"method"};

    /**
     * 重写构造函数
     */
    public MyMethodRoutePredicateFactory() {
        super(MyMethodRoutePredicateFactory.Config.class);
    }


    /**
     * 谓词条件判断
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(MyMethodRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                ServerHttpRequest request = exchange.getRequest();
                String methodValue = request.getMethodValue();
                if (methodValue.equalsIgnoreCase(config.getMethod())) {
                    return true;
                }
                return false;
            }
        };
    }


    @Override
    public List<String> shortcutFieldOrder() {
        List<String> strings = Arrays.asList(KEY_ARRAY);
        return strings;
    }

    public static class Config {

        private String method;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

}
