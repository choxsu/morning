package com.syc.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.syc.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类别表
 *
 * @author su
 * @TableName cms_category
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cms_category")
public class CmsCategory extends BaseEntity {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否有效；0是1否
     */
    private Integer status;

}