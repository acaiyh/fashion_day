package com.fashion.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.fashion.uaa.dao"})
public class UAAServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UAAServerApplication.class, args);
    }

}
