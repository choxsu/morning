package com.syc.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.syc.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author su
 * @TableName cms_article
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cms_article")
public class CmsArticle extends BaseEntity {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 账户id
     */
    private Integer accountId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容格式 1-富文本 2-markdown
     */
    private Integer contextType;

    /**
     * 内容
     */
    private String content;

    /**
     * 点击次数
     */
    private Integer clickCount;


    /**
     * 状态 0-草稿 1-发布
     */
    private Integer status;

    /**
     * 类型id
     */
    private Integer categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mo_article
     */
    private static final long serialVersionUID = 1L;
}