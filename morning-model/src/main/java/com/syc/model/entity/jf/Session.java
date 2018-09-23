

package com.syc.model.entity.jf;


import com.syc.model.entity.jf.base.BaseSession;

/**
 * session 存放在数据库中，并引入 cache 中间层，优点如下：
 * 1：简单且高性能
 * 2：支持分布式与集群
 * 3：支持服务器断电和重启
 * 4：支持 tomcat、jetty 等运行容器重启
 */
public class Session extends BaseSession<Session> {

	private static final long serialVersionUID = 1L;
	public static final Session dao = new Session().dao();
	
	/**
	 * 登录会话是否已过期
	 */
	public boolean isExpired() {
		return getExpireAt() < System.currentTimeMillis();
	}

	/**
	 * 登录会话是否未过期
	 */
	public boolean notExpired() {
		return ! isExpired();
	}
}
