package com.syc.model.entity.jf.base;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: role
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> {

    public static final String tableName = "role";

    /**
     * 该字段暂无注释
     */
    private String id = "id";
    /**
     * 该字段暂无注释
     */
    private String name = "name";
    /**
     * 该字段暂无注释
     */
    private String createAt = "createAt";


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
	public void setName(String name) {
		set(this.name, name);
	}

    /**
     * 该字段暂无注释
     */
	public String getName() {
		return getStr(name);
	}


    /**
     * 该字段暂无注释
     */
	public void setCreateAt(java.util.Date createAt) {
		set(this.createAt, createAt);
	}
	
    /**
     * 该字段暂无注释
     */
	public java.util.Date getCreateAt() {
		return get(createAt);
	}

}
