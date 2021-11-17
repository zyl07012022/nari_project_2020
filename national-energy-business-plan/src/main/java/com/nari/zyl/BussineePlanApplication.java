package com.nari.zyl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = "com.nari.zyl.mapper")
public class BussineePlanApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(BussineePlanApplication.class,args);
    }

}
