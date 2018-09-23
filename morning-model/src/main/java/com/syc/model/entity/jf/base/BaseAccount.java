package com.syc.model.entity.jf.base;

import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: account
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseAccount<M extends BaseAccount<M>> extends Model<M> {

    public static final String tableName = "account";

    /**
     * 该字段暂无注释
     */
    private String id = "id";
    /**
     * 该字段暂无注释
     */
    private String nickName = "nickName";
    /**
     * 该字段暂无注释
     */
    private String userName = "userName";
    /**
     * 该字段暂无注释
     */
    private String password = "password";
    /**
     * 该字段暂无注释
     */
    private String salt = "salt";
    /**
     * 该字段暂无注释
     */
    private String status = "status";
    /**
     * 该字段暂无注释
     */
    private String createAt = "createAt";
    /**
     * 该字段暂无注释
     */
    private String ip = "ip";
    /**
     * 该字段暂无注释
     */
    private String avatar = "avatar";
    /**
     * 被赞次数
     */
    private String likeCount = "likeCount";


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
	public void setNickName(String nickName) {
		set(this.nickName, nickName);
	}

    /**
     * 该字段暂无注释
     */
	public String getNickName() {
		return getStr(nickName);
	}


    /**
     * 该字段暂无注释
     */
	public void setUserName(String userName) {
		set(this.userName, userName);
	}

    /**
     * 该字段暂无注释
     */
	public String getUserName() {
		return getStr(userName);
	}


    /**
     * 该字段暂无注释
     */
	public void setPassword(String password) {
		set(this.password, password);
	}

    /**
     * 该字段暂无注释
     */
	public String getPassword() {
		return getStr(password);
	}


    /**
     * 该字段暂无注释
     */
	public void setSalt(String salt) {
		set(this.salt, salt);
	}

    /**
     * 该字段暂无注释
     */
	public String getSalt() {
		return getStr(salt);
	}


    /**
     * 该字段暂无注释
     */
	public void setStatus(Integer status) {
		set(this.status, status);
	}

    /**
     * 该字段暂无注释
     */
	public Integer getStatus() {
		return getInt(status);
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


    /**
     * 该字段暂无注释
     */
	public void setIp(String ip) {
		set(this.ip, ip);
	}

    /**
     * 该字段暂无注释
     */
	public String getIp() {
		return getStr(ip);
	}


    /**
     * 该字段暂无注释
     */
	public void setAvatar(String avatar) {
		set(this.avatar, avatar);
	}

    /**
     * 该字段暂无注释
     */
	public String getAvatar() {
		return getStr(avatar);
	}


    /**
     * set被赞次数
     */
	public void setLikeCount(Integer likeCount) {
		set(this.likeCount, likeCount);
	}

    /**
     * get被赞次数
     */
	public Integer getLikeCount() {
		return getInt(likeCount);
	}

}
