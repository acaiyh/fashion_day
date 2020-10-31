package com.fashion.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * EnableDiscoveryClient 和 EnableEurekaClient 差不过
 *      共同点：都可以注册服务到注册中心
 *      不同点：EnableEurekaClient 只适用注册到Eureka,
 *              但是EnableDiscoveryClient 可以注册服务到其他中心 like Zookeeper, Consul
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}
