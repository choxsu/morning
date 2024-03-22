package com.syc.modules.admin.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.syc.modules.common.base.BaseEntity;
import lombok.Data;

/**
 * 字典类型实体
 *
 * @author xq.su
 * @since 2022/12/17
 */
@Data
public class SysDictType extends BaseEntity {
    /**
     * 主键 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型编码
     */
    private String code;

    /**
     * 状态(0:正常;1:禁用)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}