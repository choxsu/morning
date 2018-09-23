package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: blog_tag
 * remarks: 标签表
 */
@SuppressWarnings("serial")
public abstract class BaseBlogTag<M extends BaseBlogTag<M>> extends Model<M> {

    public static final String tableName = "blog_tag";

    /**
     * 主键id
     */
    private String id = "id";
    /**
     * 名称
     */
    private String name = "name";
    /**
     * 是否有效；0是1否
     */
    private String status = "status";


    /**
     * set主键id
     */
	public void setId(Integer id) {
		set(this.id, id);
	}

    /**
     * get主键id
     */
	public Integer getId() {
		return getInt(id);
	}


    /**
     * set名称
     */
	public void setName(String name) {
		set(this.name, name);
	}

    /**
     * get名称
     */
	public String getName() {
		return getStr(name);
	}


    /**
     * set是否有效；0是1否
     */
	public void setStatus(Integer status) {
		set(this.status, status);
	}

    /**
     * get是否有效；0是1否
     */
	public Integer getStatus() {
		return getInt(status);
	}

}
