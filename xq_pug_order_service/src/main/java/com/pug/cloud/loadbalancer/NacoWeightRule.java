//package com.pug.cloud.loadbalancer;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.cloud.nacos.NacosServiceInstance;
////import com.alibaba.cloud.nacos.ribbon.NacosServer;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import com.alibaba.nacos.client.naming.NacosNamingService;
//import com.alibaba.nacos.common.utils.CollectionUtils;
////import com.alibaba.nacos.common.utils.MapUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.*;
//import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
//import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
////import org.springframework.http.HttpHeaders;
////import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//import java.util.List;
////import java.util.Map;
//
//@Slf4j
//public class NacoWeightRule extends RoundRobinLoadBalancer {
//
//    private String serviceId;
//    private ObjectProvider<ServiceInstanceListSupplier> selectOneHealthyInstance;
//
//    public NacoWeightRule(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
//        super(serviceInstanceListSupplierProvider, serviceId);
//        this.serviceId = serviceId;
//        this.selectOneHealthyInstance = serviceInstanceListSupplierProvider;
//    }
//
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    @Override
//    public Mono<Response<ServiceInstance>> choose(Request request) {
//        ServiceInstanceListSupplier supplier = selectOneHealthyInstance
//                .getIfAvailable(NoopServiceInstanceListSupplier::new);
//        return supplier.get(request).next().map(serviceInstances -> getInstanceResponse(serviceInstances, request));
//    }
//
//    Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
//
//        if (CollectionUtils.isEmpty(instances)) {
//            log.warn("No instance available {}", serviceId);
//            return new EmptyResponse();
//        }
//
//        try {
//            // ????????????nacos??????????????????
//            NamingService namingService = new NacosNamingService(nacosDiscoveryProperties.getNacosProperties());
//            // ??????serverId???????????????????????????????????????????????????????????????????????????????????? ????????? + ?????????
//            Instance instance = namingService.selectOneHealthyInstance(serviceId);
//            // ??????nacso???????????????????????????
//            NacosServiceInstance nacosServiceInstance = new NacosServiceInstance();
//            nacosServiceInstance.setHost(instance.getIp());
//            nacosServiceInstance.setServiceId(instance.getServiceName());
//            nacosServiceInstance.setPort(instance.getPort());
//            nacosServiceInstance.setMetadata(instance.getMetadata());
//            log.info("??????????????????????????????????????????{}????????????{}", instance.getServiceName(), instance.getPort());
//            return new DefaultResponse(nacosServiceInstance);
//        } catch (Exception ex) {
//            return super.choose(request).block();
//        }
//
//    }
//}
