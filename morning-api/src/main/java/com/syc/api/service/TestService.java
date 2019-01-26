package com.syc.api.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {

    //    @Autowired
//    private RedisLock redisLock;
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 抢购代码
     * @param key pronum 首先用客户端设置数量
     * @return
     */
    public boolean seckill(String key) {

        RLock fairLock = redissonClient.getFairLock(key);
        try {
            if (fairLock.tryLock(100, TimeUnit.SECONDS)) {
                Object pronum = redisTemplate.opsForValue().get("pronum");
                if (pronum == null) {
                    System.out.println("没有库存可抢购！");
                    return false;
                }
                Integer pro = (Integer) pronum;
                //修改库存
                if (pro - 1 >= 0) {
                    redisTemplate.opsForValue().set("pronum", String.valueOf(pro - 1));
                    System.out.println("库存数量:" + pronum + "     成功!!!" + Thread.currentThread().getName());
                } else {
                    System.out.println("手慢拍大腿");
                }
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
        return false;
    }
}
