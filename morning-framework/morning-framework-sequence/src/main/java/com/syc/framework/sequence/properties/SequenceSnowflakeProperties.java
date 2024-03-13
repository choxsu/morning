package com.syc.framework.sequence.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Snowflake 发号器属性
 * @author BCTC
 */
@Component
@ConfigurationProperties(prefix = "morning.sequence.snowflake")
public class SequenceSnowflakeProperties {

    /**
     * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
     */
    private long dataCenterId;

    /**
     * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
     */
    private long workerId;

    /**
     * 是否允许睡眠
     */
    private boolean enableThread;


    public long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public boolean isEnableThread() {
        return enableThread;
    }

    public void setEnableThread(boolean enableThread) {
        this.enableThread = enableThread;
    }
}
