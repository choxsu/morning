package com.xxl.job.admin.core.exception;

/**
 * @author xuxueli 2019-05-04 23:19:29
 */
public class XxlJobException extends RuntimeException {

    private int code = 500;

    public XxlJobException() {

    }

    public XxlJobException(String message) {
        super(message);
    }

    public XxlJobException(int code, String message) {
        super(message);
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
