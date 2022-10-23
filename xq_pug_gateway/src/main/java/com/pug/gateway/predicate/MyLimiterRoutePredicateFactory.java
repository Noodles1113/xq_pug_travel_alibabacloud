package com.pug.gateway.predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
// 自定义谓词
public class MyLimiterRoutePredicateFactory extends AbstractRoutePredicateFactory<MyLimiterRoutePredicateFactory.Config> {

    public static final String[] KEY_ARRAY = {"minId", "maxId"};

    /**
     * 重写构造函数
     */
    public MyLimiterRoutePredicateFactory() {
        super(MyLimiterRoutePredicateFactory.Config.class);
    }


    /**
     * 谓词条件判断
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(MyLimiterRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                // 获取请求对象
                ServerHttpRequest request = exchange.getRequest();
                // 获取请求的参数，getQueryParams()：user/get?id=1&name=2323
                //String id = request.getQueryParams().getFirst("id");
                //String name = request.getQueryParams().getFirst("name");
                // 得到请求参数的用户id：/user/get/1
                RequestPath path = request.getPath();
                String paramUserId = path.pathWithinApplication().elements().get(5).value();
                if (null != paramUserId) {
                    Long numberId = Long.parseLong(paramUserId);
                    if (numberId >= config.getMinId() && numberId <= config.getMaxId()) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * 配置参数注册位置，读取配置applicaiton.yml中的值，然后自动赋值到Config属性对象中
     * predicates:
     * - MyLimiter=0,1000
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        // 获取两个属性名，放入带list,然后通过前缀找到Config配置类，在调用set方法给对应的属性赋值
        List<String> strings = Arrays.asList(KEY_ARRAY);
        return strings;
    }


    public static class Config {

        private Long minId;
        private Long maxId;

        public Long getMinId() {
            return minId;
        }

        public void setMinId(Long minId) {
            this.minId = minId;
        }

        public Long getMaxId() {
            return maxId;
        }

        public void setMaxId(Long maxId) {
            this.maxId = maxId;
        }
    }

}
