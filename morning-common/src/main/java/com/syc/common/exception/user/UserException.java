package com.syc.common.exception.user;

import com.syc.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author xq.su
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
