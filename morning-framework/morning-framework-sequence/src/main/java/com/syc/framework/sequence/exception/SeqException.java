package com.syc.framework.sequence.exception;

/**
 * 序列号生成异常
 * @author BCTC
 */
public class SeqException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SeqException(String message) {
        super(message);
    }

    public SeqException(Throwable cause) {
        super(cause);
    }

}
