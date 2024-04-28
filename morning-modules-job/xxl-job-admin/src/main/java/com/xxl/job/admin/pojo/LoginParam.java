package com.xxl.job.admin.pojo;

/**
 * @author xiaoqiu.su
 * created on 2024/4/26
 */
public class LoginParam {

    private String userName;
    String password;
    Boolean ifRemember = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIfRemember() {
        return ifRemember;
    }

    public void setIfRemember(Boolean ifRemember) {
        this.ifRemember = ifRemember;
    }
}
