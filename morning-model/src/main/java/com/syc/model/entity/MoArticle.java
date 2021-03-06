package com.syc.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * null
 * @TableName mo_article
 */
@Data
public class MoArticle implements Serializable {
    /**
     * 主键id
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer id;

    /**
     * 账户id
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer account_id;

    /**
     * 标题
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private String title;

    /**
     * 内容格式 1-富文本 2-markdown
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer context_type;

    /**
     * 内容
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private String content;

    /**
     * 点击次数
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer click_count;

    /**
     * 是否删除 0否 1是
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer is_delete;

    /**
     * 状态 0-草稿 1-发布
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer status;

    /**
     * 类型id
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Integer category_id;

    /**
     * 创建时间
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Date create_at;

    /**
     * 修改时间
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private Date update_at;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mo_article
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    private static final long serialVersionUID = 1L;
}