package com.syc.config;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.syc.model.entity.jf._MappingKit;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author choxsu
 * @date 2018/9/14 0014
 */
@Configuration
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ActiveRecordPlugin loaderARPlugin(){
        ActiveRecordPlugin arp = new ActiveRecordPlugin(this.dataSource);
        arp.setDialect(new MysqlDialect());
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        _MappingKit.mapping(arp);
        arp.setShowSql(true);
        arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
        // arp.addSqlTemplate("/sql/all_sqls.sql");
        arp.start();
        return arp;
    }


    @Bean
    public RedisTemplate redisTemplateInit(RedisTemplate redisTemplate) {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password:''}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StrKit.notBlank(password)) {
            singleServerConfig.setPassword(password);
        }

        config.setLockWatchdogTimeout(15 * 1000);
        return Redisson.create(config);
    }

}
