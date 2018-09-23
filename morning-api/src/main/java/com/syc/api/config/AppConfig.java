package com.syc.api.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.template.source.ClassPathSourceFactory;
import com.syc.model.entity.jf._MappingKit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * @author choxsu
 * @date 2018/9/14 0014
 */
@Configuration
public class AppConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * Caffeine 换成bean 配置
     * <p>
     * <p>
     * Caffeine配置说明：
     * <p>
     * initialCapacity=[integer]: 初始的缓存空间大小
     * maximumSize=[long]: 缓存的最大条数
     * maximumWeight=[long]: 缓存的最大权重
     * expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
     * expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
     * refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
     * weakKeys: 打开key的弱引用
     * weakValues：打开value的弱引用
     * softValues：打开value的软引用
     * recordStats：开发统计功能
     * <p>
     * 注意：
     * expireAfterWrite和expireAfterAccess同事存在时，以expireAfterWrite为准。
     * maximumSize和maximumWeight不可以同时使用
     * weakValues和softValues不可以同时使用
     * <p>
     * <p>
     * 必须要指定这个Bean，refreshAfterWrite=5s这个配置属性才生效
     *
     * @return
     */
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {

        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {

            @Override
            public Object load(Object key) {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) {
                return oldValue;
            }
        };

        return cacheLoader;
    }

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


    /* 下面的代码是示例代码 会被移除掉的*/

    /**
     * 实例代码
     */
    @Service
    public static class PersonService {
        private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

//        @Autowired
//        PersonRepository personRepository;

        @CachePut(value = "people", key = "#person.id")
        public Person save(Person person) {
            Person p = person;
            logger.info("为id、key为:" + p.getId() + "数据做了缓存");
            return p;
        }


        @CacheEvict(value = "people")//2
        public void remove(Long id) {
            logger.info("删除了id、key为" + id + "的数据缓存");
            //这里不做实际删除操作
        }

        /**
         * Cacheable
         * value：缓存key的前缀。
         * key：缓存key的后缀。
         * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
         */
        @Cacheable(value = "people", key = "#person.id", sync = true)
        public Person findOne(Person person, String a, String[] b, List<Long> c) {
            Person p = person;
            logger.info("为id、key为:" + p.getId() + "数据做了缓存");
            return p;
        }

        @Cacheable(value = "people1")//3
        public Person findOne1() {
            Person p = new Person();
            p.setId(2);
            logger.info("为id、key为:" + p.getId() + "数据做了缓存");
            return p;
        }

        @Cacheable(value = "people2")//3
        public Person findOne2(Person person) {
            Person p = person;
            logger.info("为id、key为:" + p.getId() + "数据做了缓存");
            return p;
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    static
    class Person {
        private Integer id;

        private String name;

    }

}
