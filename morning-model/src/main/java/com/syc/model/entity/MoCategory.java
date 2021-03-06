package com.syc.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 类别表
 * @TableName mo_category
 */
@Data
public class MoCategory implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer id;

    /**
     * 名称
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private String name;

    /**
     * 是否有效；0是1否
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mo_category
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private static final long serialVersionUID = 1L;
}