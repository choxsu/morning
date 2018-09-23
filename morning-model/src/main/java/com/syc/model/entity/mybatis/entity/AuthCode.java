package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * (AuthCode)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class AuthCode implements Serializable {
    private static final long serialVersionUID = -65735511045005123L;
    
    private String id;
    
    private Integer accountid;
    
    private Long expireat;
    
    private Integer type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Long getExpireat() {
        return expireat;
    }

    public void setExpireat(Long expireat) {
        this.expireat = expireat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}