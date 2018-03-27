package com.example.multisource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    public static final String CACHE_1="students";

    public static final String CACHE_2="users";

    @Bean
    @ConfigurationProperties(prefix="spring.redis.basic")
    public JedisConnectionFactory jedisConnectionFactory()
    {
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory();
        connectionFactory.setPassword("123456");
        connectionFactory.setHostName("192.168.46.128");
        connectionFactory.setPort(6379);
        connectionFactory.setTimeout(3000);
        connectionFactory.setDatabase(0);
        connectionFactory.setUsePool(true);
        return connectionFactory;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.redis.pool")
    public JedisPoolConfig  jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(500);
        jedisPoolConfig.setMinIdle(50);
        return jedisPoolConfig;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager()
    {
        RedisCacheManager cacheManager=new RedisCacheManager( redisTemplate(jedisConnectionFactory()));
        List<String> cacheNames=new ArrayList<>();
        cacheNames.add(RedisConfiguration.CACHE_1);
        cacheNames.add(RedisConfiguration.CACHE_2);
        cacheManager.setCacheNames(cacheNames);
        return cacheManager;
    }


    @Bean
    public KeyGenerator keyGenerator()
    {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sBuilder=new StringBuilder();
                sBuilder.append(o.getClass().getName()).append(".");
                sBuilder.append(method.getName()).append(".");
                for (Object obj : objects) {
                    sBuilder.append(obj.toString()).append(".");
                }
                return sBuilder.toString();
            }
        };
    }
}
