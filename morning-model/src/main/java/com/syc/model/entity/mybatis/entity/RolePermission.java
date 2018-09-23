package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -17250067977097487L;
    
    private Integer roleid;
    
    private Integer permissionid;


    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

}