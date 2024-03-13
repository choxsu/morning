package com.syc.framework.sequence.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.syc.framework.sequence.Sequence;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 自定义雪花id生成
 * 使用sequence
 * @author BCTC
 */
@Component
public class SnowFlakeIdGenerator implements IdentifierGenerator {

    @Resource
    private Sequence sequence;

    @Override
    public Number nextId(Object entity) {

        return sequence.nextValue();
    }
}
