package com.syc.common.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author choxsu
 * @date 2019/01/26
 */
@Component
public class RedissonManager {

    @Value("spring.redis.host")
    private String host;
    @Value("spring.redis.port")
    private String port;
    @Value("spring.redis.password")
    private String password;

    //获取redisson对象的方法
    @Bean
    public Redisson getRedisson() {
        return (Redisson) Redisson.create(initConfig());
    }


    private Config initConfig() {
        String adds = host + ":" + port;
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(adds).setPassword(password);
        return config;
    }


}
