package com.syc.model.entity.mybatis.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:39
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -43697462099922730L;
    
    private Integer id;
    
    private String nickname;
    
    private String username;
    
    private String password;
    
    private String salt;
    
    private Integer status;
    
    private Date createat;
    
    private String ip;
    
    private String avatar;
    //被赞次数
    private Integer likecount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

}