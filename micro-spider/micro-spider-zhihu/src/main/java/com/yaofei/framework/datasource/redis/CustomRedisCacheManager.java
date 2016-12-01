package com.yaofei.framework.datasource.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 的缓存管理器配置（主要目的用来配置各个管理器中缓存的有效时间），若使用不同的有效期，可配置相应的缓存管理器
 *
 * @author fei.yao
 */
@Configuration
@EnableCaching
public class CustomRedisCacheManager {

    /**
     * 该 redis缓存管理器 中缓存的有效期是 86400秒（即：1天）
     *
     * @param redisTemplate
     * @return
     */
    @Primary
    @Bean(name = "dayCache")
    public CacheManager dayCache(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(86400);
        return cacheManager;
    }

//    /**
//     * 测试
//     *
//     * @param redisTemplate
//     * @return
//     */
//    @Bean(name = "test")
//    public CacheManager test(RedisTemplate<Object, Object> redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(60); // 设置key-value超时时间
//        return cacheManager;
//    }
}
