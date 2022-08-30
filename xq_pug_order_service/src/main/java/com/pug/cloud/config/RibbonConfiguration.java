package com.pug.cloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@LoadBalancerClients({
        @LoadBalancerClient(value = "xq-pug-user-service", configuration = UserRandomLoadbalancerConfig.class),
})
public class RibbonConfiguration {

}

class UserRandomLoadbalancerConfig {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        // 拿到你要处理的服务实列 其实也就是：xq-pug-user-service
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        // 你选择什么样子的策略来执行你的服务 RandomLoadBalancer 选择随机
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
