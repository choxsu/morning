package com.syc.model.entity.jf.base;


import com.jfinal.plugin.activerecord.Model;

/**
 * @author choxsu, do not modify this file.
 * table: visitor
 * remarks: 
 */
@SuppressWarnings("serial")
public abstract class BaseVisitor<M extends BaseVisitor<M>> extends Model<M> {

    public static final String tableName = "visitor";

    /**
     * 主键id
     */
    private String id = "id";
    /**
     * 请求的IP地址
     */
    private String ip = "ip";
    /**
     * 请求的页面路径
     */
    private String url = "url";
    /**
     * 请求方法
     */
    private String method = "method";
    /**
     * 请求的客户端
     */
    private String client = "client";
    /**
     * 请求时间
     */
    private String requestTime = "requestTime";


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
     * set请求的IP地址
     */
	public void setIp(String ip) {
		set(this.ip, ip);
	}

    /**
     * get请求的IP地址
     */
	public String getIp() {
		return getStr(ip);
	}


    /**
     * set请求的页面路径
     */
	public void setUrl(String url) {
		set(this.url, url);
	}

    /**
     * get请求的页面路径
     */
	public String getUrl() {
		return getStr(url);
	}


    /**
     * set请求方法
     */
	public void setMethod(String method) {
		set(this.method, method);
	}

    /**
     * get请求方法
     */
	public String getMethod() {
		return getStr(method);
	}


    /**
     * set请求的客户端
     */
	public void setClient(String client) {
		set(this.client, client);
	}

    /**
     * get请求的客户端
     */
	public String getClient() {
		return getStr(client);
	}


    /**
     * set请求时间
     */
	public void setRequestTime(java.util.Date requestTime) {
		set(this.requestTime, requestTime);
	}
	
    /**
     * get请求时间
     */
	public java.util.Date getRequestTime() {
		return get(requestTime);
	}

}
