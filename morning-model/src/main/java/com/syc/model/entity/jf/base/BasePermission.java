package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: permission
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BasePermission<M extends BasePermission<M>> extends Model<M> {

    public static final String tableName = "permission";

    /**
     * 该字段暂无注释
     */
    private String id = "id";
    /**
     * 该字段暂无注释
     */
    private String actionKey = "actionKey";
    /**
     * 该字段暂无注释
     */
    private String controller = "controller";
    /**
     * 该字段暂无注释
     */
    private String remark = "remark";


    /**
     * 该字段暂无注释
     */
	public void setId(Integer id) {
		set(this.id, id);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getId() {
		return getInt(id);
	}


    /**
     * 该字段暂无注释
     */
	public void setActionKey(String actionKey) {
		set(this.actionKey, actionKey);
	}

    /**
     * 该字段暂无注释
     */
	public String getActionKey() {
		return getStr(actionKey);
	}


    /**
     * 该字段暂无注释
     */
	public void setController(String controller) {
		set(this.controller, controller);
	}

    /**
     * 该字段暂无注释
     */
	public String getController() {
		return getStr(controller);
	}


    /**
     * 该字段暂无注释
     */
	public void setRemark(String remark) {
		set(this.remark, remark);
	}

    /**
     * 该字段暂无注释
     */
	public String getRemark() {
		return getStr(remark);
	}

}
