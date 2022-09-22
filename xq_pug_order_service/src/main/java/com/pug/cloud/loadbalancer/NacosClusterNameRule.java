//package com.pug.cloud.loadbalancer;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.cloud.nacos.NacosServiceInstance;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import com.alibaba.nacos.client.naming.NacosNamingService;
//import com.alibaba.nacos.client.naming.core.Balancer;
//import com.alibaba.nacos.common.utils.CollectionUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.DefaultResponse;
//import org.springframework.cloud.client.loadbalancer.EmptyResponse;
//import org.springframework.cloud.client.loadbalancer.Request;
//import org.springframework.cloud.client.loadbalancer.Response;
//import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
//import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import reactor.core.publisher.Mono;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Slf4j
//public class NacosClusterNameRule extends RoundRobinLoadBalancer {
//
//    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
//    private final String serviceId;
//
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    public NacosClusterNameRule(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
//                                String serviceId) {
//        super(serviceInstanceListSupplierProvider, serviceId);
//        this.serviceId = serviceId;
//        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
//    }
//
//
//    @Override
//    public Mono<Response<ServiceInstance>> choose(Request request) {
//        try {
//            ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
//                    .getIfAvailable(NoopServiceInstanceListSupplier::new);
//            return supplier.get(request).next().map(serviceInstances -> getInstanceResponse(serviceInstances, request));
//        } catch (Exception ex) {
//            // 轮询做底
//            return super.choose(request);
//        }
//    }
//
//    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
//
//        try {
//            if (instances.isEmpty()) {
//                if (log.isWarnEnabled()) {
//                    log.warn("No servers available for service: " + this.serviceId);
//                }
//                return new EmptyResponse();
//            }
//            // 1: 拿到你当前服务实例的集群名称
//            String currentClusterName = nacosDiscoveryProperties.getClusterName();
//            // 2: 获取所有的服务实例
//            NamingService namingService = new NacosNamingService(nacosDiscoveryProperties.getNacosProperties());
//            List<Instance> selectInstances = namingService.selectInstances(serviceId, true);
//            if (CollectionUtils.isEmpty(selectInstances)) {
//                log.warn("not found 对应的 {} 实例", serviceId);
//                return new EmptyResponse();
//            }
//            // 3: 根据serviceId从nacos所有的服务实例中过滤出集群名字相同的服务实例,并且是存活的
//            List<Instance> sameClusterInstances = selectInstances.stream()
//                    .filter(instance -> Objects.equals(currentClusterName, instance.getClusterName()))
//                    .collect(Collectors.toList());
//            // 4: 开始进行判断
//            List<Instance> chooseIntances = new ArrayList<>();
//            if (CollectionUtils.isEmpty(sameClusterInstances)) {
//                // 如果没有就跨集群调用
//                chooseIntances = selectInstances;
//                log.info("当前实例是：clusterName:{},serviceId:{} ，跨集群调用", currentClusterName, serviceId);
//            } else {
//                // 如果同城有相应的服务
//                chooseIntances = sameClusterInstances;
//                log.info("当前实例是：clusterName:{},serviceId:{} ，集群调用", currentClusterName, serviceId);
//            }
//            // 5: 不论是同城还是跨城按照权重进行返回的
//            Instance selectInstance = ExpandBalancer.getHostByRandomWeightChoose(chooseIntances);
//            // 6: 把按照权重选择的selectInstance放入到nacos的服务实例中返回
//            NacosServiceInstance nacosServiceInstance = new NacosServiceInstance();
//            nacosServiceInstance.setHost(selectInstance.getIp());
//            nacosServiceInstance.setServiceId(selectInstance.getServiceName());
//            nacosServiceInstance.setPort(selectInstance.getPort());
//            nacosServiceInstance.setMetadata(selectInstance.getMetadata());
//            log.info("集群后基于权重的负载均衡，服务名：{}，端口：{}", selectInstance.getServiceName(), selectInstance.getPort());
//            return new DefaultResponse(nacosServiceInstance);
//        } catch (NacosException e) {
//            log.error("服务实例找不到了");
//            return null;
//        }
//    }
//
//}
//
//class ExpandBalancer extends Balancer {
//    public static Instance getHostByRandomWeightChoose(List<Instance> hosts) {
//        return getHostByRandomWeight(hosts);
//    }
//}
