package com.syc.framework.sequence.builder;

import com.syc.framework.sequence.Sequence;

/**
 * 序列号生成器构建者
 * @author BCTC
 */
public interface SeqBuilder {

    /**
     * 构建一个序列号生成器
     *
     * @return 序列号生成器
     */
    Sequence build();

}
