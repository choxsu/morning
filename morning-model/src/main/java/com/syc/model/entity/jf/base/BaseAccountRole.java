package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: account_role
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseAccountRole<M extends BaseAccountRole<M>> extends Model<M> {

    public static final String tableName = "account_role";

    /**
     * 该字段暂无注释
     */
    private String accountId = "accountId";
    /**
     * 该字段暂无注释
     */
    private String roleId = "roleId";


    /**
     * 该字段暂无注释
     */
	public void setAccountId(Integer accountId) {
		set(this.accountId, accountId);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getAccountId() {
		return getInt(accountId);
	}


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

}
