package com.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FashionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionServerApplication.class, args);
    }

}
