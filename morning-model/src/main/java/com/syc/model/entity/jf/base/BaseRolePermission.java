package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: role_permission
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseRolePermission<M extends BaseRolePermission<M>> extends Model<M> {

    public static final String tableName = "role_permission";

    /**
     * 该字段暂无注释
     */
    private String roleId = "roleId";
    /**
     * 该字段暂无注释
     */
    private String permissionId = "permissionId";


    /**
     * 该字段暂无注释
     */
	public void setRoleId(Integer roleId) {
		set(this.roleId, roleId);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getRoleId() {
		return getInt(roleId);
	}


    /**
     * 该字段暂无注释
     */
	public void setPermissionId(Integer permissionId) {
		set(this.permissionId, permissionId);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getPermissionId() {
		return getInt(permissionId);
	}

}
