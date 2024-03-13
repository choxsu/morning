package com.syc.framework.sequence.builder;

import com.syc.framework.sequence.Sequence;
import com.syc.framework.sequence.impl.SnowflakeSequence;

/**
 * 基于雪花算法，序列号生成器构建者
 * @author BCTC
 */
public class SnowflakeSeqBuilder implements SeqBuilder {

    /**
     * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
     */
    private long datacenterId;

    /**
     * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
     */
    private long workerId;

    /**
     * 是否允许睡眠1ms
     * @return
     */
    private boolean enableThread;

    public static SnowflakeSeqBuilder create() {
        return new SnowflakeSeqBuilder();
    }

    @Override
    public Sequence build() {
        SnowflakeSequence sequence = new SnowflakeSequence();
        sequence.setDataCenterId(this.datacenterId);
        sequence.setWorkerId(this.workerId);
        sequence.setEnableThread(this.enableThread);
        return sequence;
    }

    public SnowflakeSeqBuilder datacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
        return this;
    }

    public SnowflakeSeqBuilder workerId(long workerId) {
        this.workerId = workerId;
        return this;
    }

    public SnowflakeSeqBuilder enableThread(boolean enableThread) {
        this.enableThread = enableThread;
        return this;
    }

}
