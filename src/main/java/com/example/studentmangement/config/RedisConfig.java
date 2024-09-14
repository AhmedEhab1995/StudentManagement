package com.example.studentmangement.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    private final int redisPort;
    private final String redisHost;

    public RedisConfig(@Value("${spring.data.redis.port:6379}") int redisPort, @Value("${spring.data.redis.host:localhost}") String redisHost) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        return Redisson.create(config);
    }
}
