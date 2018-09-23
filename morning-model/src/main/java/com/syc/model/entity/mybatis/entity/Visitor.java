package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Visitor)实体类
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public class Visitor implements Serializable {
    private static final long serialVersionUID = 476936777771346837L;
    //主键id
    private Integer id;
    //请求的IP地址
    private String ip;
    //请求的页面路径
    private String url;
    //请求方法
    private String method;
    //请求的客户端
    private String client;
    //请求时间
    private Date requesttime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

}