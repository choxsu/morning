package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 377314727163993568L;
    
    private Integer id;
    
    private String name;
    
    private Date createat;


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

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

}