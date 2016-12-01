package com.yaofei;

import com.yaofei.spider.zhihu.ZhihuDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 爬虫项目入口
 */

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class MicroSpiderApplication implements CommandLineRunner {

    @Autowired
    ZhihuDoService zhihuDoService;

    @Override
    public void run(String... strings) throws Exception {
        zhihuDoService.run();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroSpiderApplication.class, args);
    }
}
