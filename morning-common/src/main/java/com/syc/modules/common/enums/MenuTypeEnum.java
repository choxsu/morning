package com.syc.modules.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.syc.modules.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author xq.su
 * @since 2022/4/23 9:36
 */

public enum MenuTypeEnum implements IBaseEnum<Integer> {

    /**
     * null 菜单 目录 外链 按钮
     */
    NULL(0, null),
    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    EXTLINK(3, "外链"),
    BUTTON(4, "按钮");

    /**
     * Mybatis-Plus 提供注解表示插入数据库时插入该值
     */
    @Getter
    @EnumValue
    private final Integer value;

    @Getter
    private final String label;

    MenuTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
