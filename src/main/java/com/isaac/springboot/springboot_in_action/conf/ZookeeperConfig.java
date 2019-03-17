package com.isaac.springboot.springboot_in_action.conf;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//InterProcessMutex——实现分布式锁
//@Configuration
public class ZookeeperConfig {
    @Value("${zk.url}")
    private String zkUrl;

    @Bean
    public CuratorFramework getCuratorFramework(){
        //用于重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        //调用start连接zookeeper
        client.start();
        this.leaderSelect(client);
        return client;
    }
    //领导节点选取
    private void leaderSelect(CuratorFramework client){
        LeaderSelectorListenerAdapter listener = new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("get leadership");
            }
        };
        LeaderSelector selector = new LeaderSelector(client,"/schedule",listener);
        selector.autoRequeue();
        selector.start();
    }

    //服务注册
    public static void registerService(CuratorFramework client) throws Exception{
        //构造一个服务描述
        ServiceInstanceBuilder<Map> service = ServiceInstance.builder();
        service.address("192.168.1.100");
        service.port(8080);
        service.name("book");
        Map config = new HashMap();
        config.put("url","/api/v1/book");
        service.payload(config);

        ServiceInstance<Map> instance = service.build();
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class).client(client).serializer(new JsonInstanceSerializer<>(Map.class)).basePath("/service").build();

        //服务注册
        serviceDiscovery.registerService(instance);
        serviceDiscovery.start();
    }

    //获取服务
    public static ServiceInstance<Map> findService(CuratorFramework client, String serviceName) throws  Exception{
        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class).client(client).serializer(new JsonInstanceSerializer<>(Map.class)).basePath("/service").build();
        serviceDiscovery.start();

        //获取当前所有服务
        Collection<ServiceInstance<Map>> all = serviceDiscovery.queryForInstances(serviceName);
        if(all.size() == 0){
            return null;
        } else {
            //获取第一个服务
            ServiceInstance<Map> service = new ArrayList<>(all).get(0);
            return service;
        }
    }
}
