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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
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


    @Bean
    public RedisTemplate redisTemplateInit(RedisTemplate redisTemplate) {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
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

    public static class MyObject{
        private static MyObject myObject;

        //加上synchronize才能保证在多线程的情况下保证得到的对象单列，就如下面的列子
        public synchronized static MyObject getInstance(){
            if (myObject == null){
                myObject = new MyObject();
            }
            return myObject;
        }
    }

    public static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println(MyObject.getInstance().hashCode());
        }
    }

    public static void main(String[] args) {
        List<MyThread> list = new ArrayList();
        MyThread t;
        for (int i = 0; i < 50; i++) {
            t = new MyThread();
            list.add(t);
        }

        System.out.println(list.size());

        for (MyThread myThread : list) {
            myThread.start();
        }

        /*MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();*/
    }
}
