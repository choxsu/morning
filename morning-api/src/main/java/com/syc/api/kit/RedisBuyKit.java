package com.syc.api.kit;

import com.syc.api.service.common.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisBuyKit {

    private final static Logger logger = LoggerFactory.getLogger(RedisBuyKit.class);

    @Autowired
    @Qualifier("redisService")
    private RedisService redisService;

    /**
     * * @Title: checkSoldCountByRedisDate
     * * @Description: 抢购的计数处理（用于处理超卖）
     *
     * @param key        购买计数的key
     * @param limitCount 总的限购数量
     * @param buyCount   当前购买数量
     * @param endDate    抢购结束时间
     * @param lock       锁的名称与unDieLock方法的lock相同
     * @param expire     锁占有的时长（毫秒）
     * @return boolean 返回类型
     * @throws
     */
    public boolean checkSoldCountByRedisDate(String key,
                                              int limitCount,
                                              int buyCount,
                                              Date endDate,
                                              String lock,
                                              int expire) {
        boolean check = false;
        if (this.lock(lock, expire)) {
            Integer soldCount = (Integer) redisService.get(key);
            Integer totalSoldCount = (soldCount == null ? 0 : soldCount) + buyCount;
            if (totalSoldCount <= limitCount) {
                redisService.set(key, totalSoldCount, DateKit.diffDateTime(endDate, new Date()));
                check = true;
            }
            redisService.remove(lock);
        } else {
            if (this.unDieLock(lock)) {
                logger.info("解决了出现的死锁");
            } else {
                throw new RuntimeException("活动太火爆啦,请稍后重试");
            }
        }
        return check;
    }

    /**
     * @param lock   锁的名称	 *
     * @param expire 锁占有的时长（毫秒）	 *
     * @return Boolean 返回类型
     * @Title: lock     *
     * @Description: 加锁机制     *
     */
    private Boolean lock(final String lock, final int expire) {
        RedisTemplate redisTemplate = redisService.getRedisTemplate();
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                boolean locked = false;
                byte[] lockValue = redisTemplate.getValueSerializer().serialize(DateKit.getDateAddMillSecond(null, expire));
                byte[] lockName = redisTemplate.getStringSerializer().serialize(lock);
                locked = connection.setNX(lockName, lockValue);
                if (locked) connection.expire(lockName, TimeoutUtils.toSeconds(expire, TimeUnit.MILLISECONDS));
                return locked;
            }
        });
    }


    /**
     * @param @param lock 是锁的名称
     * @return Boolean 返回类型
     * @Title: unDieLock
     * @Description: 处理发生的死锁
     */
    private Boolean unDieLock(final String lock) {
        RedisTemplate redisTemplate = redisService.getRedisTemplate();
        boolean unLock = false;
        Date lockValue = (Date) redisTemplate.opsForValue().get(lock);
        if (lockValue != null && lockValue.getTime() <= (new Date().getTime())) {
            redisTemplate.delete(lock);
            unLock = true;
        }
        return unLock;
    }


}
