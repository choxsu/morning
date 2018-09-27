package com.syc.api.kit;


import com.syc.api.service.common.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component("lockRetry")
public class LockRetry {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param @param    lock 名称
     * @param expire    锁定时长（秒），建议10秒内
     * @param num       取锁重试试数，建议不大于3
     * @param interval  重试时长
     * @param forceLock 强制取锁，不建议；
     * @Title: retry
     * @Description: 重入锁
     */
    public Boolean retryLock(final String lock,
                             final int expire,
                             final int num,
                             final long interval,
                             final boolean forceLock) throws Exception {
        Date lockValue = (Date) redisTemplate.opsForValue().get(lock);
        if (forceLock) {
            redisService.remove(lock);
        }
        if (num <= 0) {
            if (null != lockValue && lockValue.getTime() >= (new Date().getTime())) {
                logger.debug(String.valueOf((lockValue.getTime() - new Date().getTime())));
                Thread.sleep(lockValue.getTime() - new Date().getTime());
                redisService.remove(lock);
                return retryLock(lock, expire, 1, interval, forceLock);
            }
            return false;
        } else {
            return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                boolean locked;
                byte[] lockValue1 = redisTemplate.getValueSerializer().serialize(DateKit.getDateAdd(null, expire, Calendar.SECOND));
                byte[] lockName = redisTemplate.getStringSerializer().serialize(lock);
                logger.debug(lockValue1.toString());
                locked = connection.setNX(lockName, lockValue1);
                if (locked) return connection.expire(lockName, TimeoutUtils.toSeconds(expire, TimeUnit.SECONDS));
                else {
                    try {
                        Thread.sleep(interval);
                        return retryLock(lock, expire, num - 1, interval, forceLock);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        return false;
                    }
                }
            });
        }
    }

}
