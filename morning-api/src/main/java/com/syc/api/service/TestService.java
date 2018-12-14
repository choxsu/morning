package com.syc.api.service;

import com.syc.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /***
     * 抢购代码
     * @param key pronum 首先用客户端设置数量
     * @return
     */
    public boolean seckill(String key) {

        RedisLock lock = new RedisLock(key, 10000, 20000);
        try {
            if (lock.lock()) {
                // 需要加锁的代码
                String pronum=lock.get("pronum");

                //修改库存
                if(Integer.parseInt(pronum)-1>=0) {
                    lock.set("pronum",String.valueOf(Integer.parseInt(pronum)-1));
                    System.out.println("库存数量:"+pronum+"     成功!!!"+Thread.currentThread().getName());
                }else {
                    System.out.println("手慢拍大腿");
                }
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
            // 操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
            lock.unlock();
        }
        return false;
    }
}
