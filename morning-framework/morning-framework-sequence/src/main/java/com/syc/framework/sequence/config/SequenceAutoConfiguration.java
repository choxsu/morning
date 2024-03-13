package com.syc.framework.sequence.config;


import com.syc.framework.sequence.Sequence;
import com.syc.framework.sequence.builder.SnowflakeSeqBuilder;
import com.syc.framework.sequence.properties.SequenceSnowflakeProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author BCTC
 */
@Configuration
@ComponentScan("com.syc.framework.sequence")
@ConditionalOnMissingBean(Sequence.class)
public class SequenceAutoConfiguration {

    /**
     * snowflak 算法作为发号器实现
     *
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnBean(SequenceSnowflakeProperties.class)
    public Sequence snowflakeSequence(SequenceSnowflakeProperties properties) {
        return SnowflakeSeqBuilder.create()
                .datacenterId(properties.getDataCenterId())
                .workerId(properties.getWorkerId())
                .enableThread(properties.isEnableThread())
                .build();
    }

}