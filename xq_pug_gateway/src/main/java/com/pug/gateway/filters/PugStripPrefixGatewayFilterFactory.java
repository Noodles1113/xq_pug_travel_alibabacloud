package com.pug.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;


@Component
@Slf4j
public class PugStripPrefixGatewayFilterFactory extends PugAbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(PugStripPrefixGatewayFilterFactory.PugConfig config) {
        GatewayFilter filter = new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                addOriginalRequestUrl(exchange, request.getURI());
                String path = request.getURI().getRawPath();
                String[] originalParts = StringUtils.tokenizeToStringArray(path, "/");
                StringBuilder newPath = new StringBuilder("/");
                for (int i = 0; i < originalParts.length; i++) {
                    if (i >= config.getParts()) {
                        // only append slash if this is the second part or greater
                        if (newPath.length() > 1) {
                            newPath.append('/');
                        }
                        newPath.append(originalParts[i]);
                    }
                }
                if (newPath.length() > 1 && path.endsWith("/")) {
                    newPath.append('/');
                }
                ServerHttpRequest newRequest = request.mutate().path(newPath.toString()).build();
                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());
                return chain.filter(exchange.mutate().request(newRequest).build());
            }
        };

        //order越小越先执行，具体所有的内部的order序号怎么查找：/actuator
        return new OrderedGatewayFilter(filter, 1);
    }


}
