package com.syc.modules.common.enums;

import com.syc.modules.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author haoxr
 * @since 2022/10/14
 */
public enum StatusEnum implements IBaseEnum<Integer> {
    /**
     * status
     */
    ENABLE(1, "启用"),
    DISABLE (0, "禁用");

    @Getter
    private final Integer value;

    @Getter
    private final String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
