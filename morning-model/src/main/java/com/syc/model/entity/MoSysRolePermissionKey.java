package com.syc.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * null
 * @TableName mo_sys_role_permission
 */
@Data
public class MoSysRolePermissionKey implements Serializable {
    /**
     * 
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    private Integer roleId;

    /**
     * 
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    private Integer permissionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mo_sys_role_permission
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    private static final long serialVersionUID = 1L;
}