package com.yaofei.heart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 爬虫项目入口
 */

@EnableEurekaServer
@SpringBootApplication
public class MicroSpiderHeartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSpiderHeartApplication.class,args);
    }
}
