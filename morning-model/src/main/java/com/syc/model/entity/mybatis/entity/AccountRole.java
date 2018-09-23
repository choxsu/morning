package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * (AccountRole)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:42
 */
public class AccountRole implements Serializable {
    private static final long serialVersionUID = 993625347496418606L;
    
    private Integer accountid;
    
    private Integer roleid;


    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

}