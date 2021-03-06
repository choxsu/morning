package com.syc.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * null
 * @TableName mo_article
 */
@Data
public class ArticleRO implements Serializable {

    /**
     * 系统用户id
     *
     */
    private Integer userId;

    /**
     * 标题
     */
    @NotNull(message = "标题必须填写")
    @Size(min = 5, max = 50, message="标题字数限制在5-50")
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
     * 状态 draft-草稿 published-发布 deleted-删除
     */
    private String status;

    /**
     * 类型id
     */
    private Integer categoryId;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改时间
     */
    private Date updateAt;

}