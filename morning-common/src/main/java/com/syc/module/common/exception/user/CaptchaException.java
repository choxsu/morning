package com.syc.module.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @author xq.su
 */
public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
