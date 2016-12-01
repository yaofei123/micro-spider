package com.yaofei.spider.zhihu;

import com.yaofei.spider.zhihu.pipeline.MysqlPipeline;
import com.yaofei.spider.zhihu.processer.ZhihuPageProcesser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Created by fei.yao on 2016/10/18.
 */
@Service
@Configurable
public class ZhihuDoService {

    @Value("${spring.redis.host}")
    private String redisHost;


    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Autowired
    ZhihuPageProcesser zhihuPageProcesser;

    @Autowired
    MysqlPipeline mysqlPipeline;

    public void run(){
        JedisPool jedisPool = new JedisPool(redisHost, redisPort);
        Spider.create(zhihuPageProcesser).scheduler(new RedisScheduler(jedisPool)).addPipeline(mysqlPipeline).thread(3).run();
    }
}
