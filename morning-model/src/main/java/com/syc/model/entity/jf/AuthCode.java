

package com.syc.model.entity.jf;


import com.syc.model.entity.jf.base.BaseAuthCode;

/**
 * 授权码，目前已用于：
 * 1：账号激活
 * 2：密码找回
 * 未来随着业务增加可添加新类型，可能需要加 data 字段传递业务所需的额外数据
 */
@SuppressWarnings("serial")
public class AuthCode extends BaseAuthCode<AuthCode> {

	public static final int TYPE_REG_ACTIVATE = 0;			// 注册激活
	public static final int TYPE_RETRIEVE_PASSWORD = 1;		// 找回密码

	/**
	 * 在保存前保障 type 正确，随着 type 的增加，需要修改此处的代码
	 */
	public boolean save() {
		int type = getType();
		if (type < TYPE_REG_ACTIVATE || type > TYPE_RETRIEVE_PASSWORD) {
			throw new RuntimeException("授权码类型不正确: " + type);
		}
		return super.save();
	}

	public boolean update() {
		throw new RuntimeException("授权码不支持更新操作");
	}

	/**
	 * 是否是有效的注册激活授权码
	 */
	public boolean isValidRegActivateAuthCode() {
		return notExpired() && isTypeRegActivate();
	}

	/**
	 * 是否是有效的密码找回授权码
	 */
	public boolean isValidRetrievePasswordAuthCode() {
		return notExpired() && isTypeRetrievePassword();
	}

	/**
	 * 是否是账号激活授权码
	 */
	public boolean isTypeRegActivate() {
		return getType() == TYPE_REG_ACTIVATE;
	}

	/**
	 * 是否是密码找回授权码
	 */
	public boolean isTypeRetrievePassword() {
		return getType() == TYPE_RETRIEVE_PASSWORD;
	}

	/**
	 * 是否已过期
	 */
	public boolean isExpired() {
		return getExpireAt() < System.currentTimeMillis();
	}

	/**
	 * 是否未过期
	 */
	public boolean notExpired() {
		return ! isExpired();
	}
}

