package com.syc.service.common;

import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author choxsu
 * @date 2019/01/26
 */
@Service("redissonService")
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public void getRedissonClient() throws IOException {
        Config config = redissonClient.getConfig();
        System.out.println(config.toJSON());
    }

    /**
     * `
     * 获取字符串对象
     *
     * @param objectName
     * @return
     */
    public <T> RBucket<T> getRBucket(String objectName) {
        return redissonClient.getBucket(objectName);
    }

    /**
     * 获取Map对象
     *
     * @param objectName
     * @return
     */
    public <K, V> RMap<K, V> getRMap(String objectName) {
        return redissonClient.getMap(objectName);
    }

    /**
     * 获取有序集合
     *
     * @param objectName
     * @return
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName) {
        return redissonClient.getSortedSet(objectName);
    }

    /**
     * 获取集合
     *
     * @param objectName
     * @return
     */
    public <V> RSet<V> getRSet(String objectName) {
        return redissonClient.getSet(objectName);
    }

    /**
     * 获取列表
     *
     * @param objectName
     * @return
     */
    public <V> RList<V> getRList(String objectName) {
        return redissonClient.getList(objectName);
    }

    /**
     * 获取队列
     *
     * @param objectName
     * @return
     */
    public <V> RQueue<V> getRQueue(String objectName) {
        return redissonClient.getQueue(objectName);
    }

    /**
     * 获取双端队列
     *
     * @param objectName
     * @return
     */
    public <V> RDeque<V> getRDeque(String objectName) {
        return redissonClient.getDeque(objectName);
    }


    /**
     * 获取锁
     *
     * @param objectName
     * @return
     */
    public RLock getRLock(String objectName) {
        return redissonClient.getLock(objectName);
    }

    public RLock getFairLock(String name) {
        return redissonClient.getFairLock(name);
    }

    /**
     * 获取读取锁
     *
     * @param objectName
     * @return
     */
    public RReadWriteLock getRWLock(String objectName) {
        return redissonClient.getReadWriteLock(objectName);
    }

    /**
     * 获取原子数
     *
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(String objectName) {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取记数锁
     *
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(String objectName) {
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     *
     * @param objectName
     * @return
     */
    public <M> RTopic<M> getRTopic(String objectName) {
        RTopic<M> rTopic = redissonClient.getTopic(objectName);
        return rTopic;
    }


}
