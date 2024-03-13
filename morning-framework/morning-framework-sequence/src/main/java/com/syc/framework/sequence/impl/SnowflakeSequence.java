package com.syc.framework.sequence.impl;

import com.syc.framework.sequence.Sequence;
import com.syc.framework.sequence.exception.SeqException;

import java.security.SecureRandom;

/**
 * 使用雪花算法 一个long类型的数据，64位。以下是每位的具体含义。 <br>
 * snowflake的结构如下(每部分用-分开): <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * （1）第一位为未使用 （2）接下来的41位为毫秒级时间(41位的长度可以使用69年) （3）然后是5位datacenterId （4）5位workerId
 * （5）最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号） <br>
 * 一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)
 *
 * @author bctc
 */
public class SnowflakeSequence implements Sequence {

    // 2018-01-01 00:00:00
    private static final long START_TIMESTAMP = 1514736000000L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private long dataCenterId;
    private long workerId;
    /**
     * 是否允许睡眠1ms
     */
    private boolean enableThread;

    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private final SecureRandom random = new SecureRandom();

    public void setDataCenterId(long dataCenterId) {
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("Data center ID must be between 0 and " + MAX_DATA_CENTER_ID);
        }
        this.dataCenterId = dataCenterId;
    }

    public void setWorkerId(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new SeqException("Worker ID must be between 0 and " + MAX_WORKER_ID);
        }
        this.workerId = workerId;
    }

    public void setEnableThread(boolean enableThread) {
        this.enableThread = enableThread;
    }

    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new SeqException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            if (this.enableThread) {
                try {
                    // 当前毫秒内的生成速度过快，稍微延迟一下
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new SeqException("Interrupted while waiting for next millisecond");
                }
                timestamp = System.currentTimeMillis();
            } else {
                sequence = (sequence + 1) & SEQUENCE_MASK;
                // 毫秒内序列溢出
                if (sequence == 0) {
                    // 阻塞到下一个毫秒,获得新的时间戳
                    timestamp = tilNextMillis(lastTimestamp);
                }
            }
        }
        if (this.enableThread) {
            // 引入随机序列
            sequence = random.nextInt(1 << SEQUENCE_BITS);
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT) |
                (dataCenterId << (SEQUENCE_BITS + WORKER_ID_BITS)) |
                (workerId << SEQUENCE_BITS) |
                sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 生成下一个序列号
     *
     * @return 序列号
     * @throws SeqException 序列号异常
     */
    @Override
    public long nextValue() throws SeqException {
        return generateId();
    }

    /**
     * 下一个生成序号（带格式）
     *
     * @return
     * @throws SeqException
     */
    @Override
    public String nextNo() throws SeqException {
        return String.valueOf(generateId());
    }

}
