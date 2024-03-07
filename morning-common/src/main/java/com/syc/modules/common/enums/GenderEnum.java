package com.syc.modules.common.enums;

import com.syc.modules.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 性别枚举
 *
 * @author haoxr
 * @since 2022/10/14
 */
@Schema(enumAsRef = true)
public enum GenderEnum implements IBaseEnum<Integer> {

    /**
     * 这个枚举 可以做成字段数据
     */
    MALE(1, "男"),
    FEMALE (2, "女");

    @Getter
    private final Integer value;

    @Getter
    private final String label;

    GenderEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
