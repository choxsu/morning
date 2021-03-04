package com.syc.config;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Duration;

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


    @Bean
    public RedissonClient getRedisson(RedisProperties redisProperties) {
        Config config = new Config();
//        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
//        if (pool == null) {
//            pool = redisProperties.getLettuce().getPool();
//        }
//        if (pool == null) {
//            pool = new RedisProperties.Pool();
//        }
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());
        // 使用默认配置会好一点，不做配置
        // 默认值：10 最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。
//        singleServerConfig.setConnectionMinimumIdleSize(pool.getMaxIdle());
        // 默认值：64 连接池最大容量。连接池的连接数量自动弹性伸缩。
//        singleServerConfig.setConnectionPoolSize(pool.getMaxActive());

        String password = redisProperties.getPassword();
        if (StrKit.notBlank(password)) {
            singleServerConfig.setPassword(password);
        }

        config.setLockWatchdogTimeout(15 * 1000);
        return Redisson.create(config);
    }

}
