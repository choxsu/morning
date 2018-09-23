package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * 标签表(BlogTag)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class BlogTag implements Serializable {
    private static final long serialVersionUID = 789715315916192862L;
    //主键id
    private Integer id;
    //名称
    private String name;
    //是否有效；0是1否
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}