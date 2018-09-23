package com.syc.model.entity.jf.base;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: session
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseSession<M extends BaseSession<M>> extends Model<M> {

    public static final String tableName = "session";

    /**
     * 该字段暂无注释
     */
    private String id = "id";
    /**
     * 该字段暂无注释
     */
    private String accountId = "accountId";
    /**
     * 该字段暂无注释
     */
    private String expireAt = "expireAt";


    /**
     * 该字段暂无注释
     */
	public void setId(String id) {
		set(this.id, id);
	}

    /**
     * 该字段暂无注释
     */
	public String getId() {
		return getStr(id);
	}


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
	public void setExpireAt(Long expireAt) {
		set(this.expireAt, expireAt);
	}

    /**
     * 该字段暂无注释
     */
	public Long getExpireAt() {
		return getLong(expireAt);
	}

}
