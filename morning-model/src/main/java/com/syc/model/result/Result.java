package com.syc.model.result;

import lombok.Getter;

@Getter
public class Result implements java.io.Serializable {

    protected static final String msgSuccess = "ok";
    protected static final String msgFailed = "fail";
    protected static final String msgNoAuth = "No authority";

    protected static final int codeNoAuth = -1;
    protected static final int codeFailed = 0;
    protected static final int codeSuccess = 1;

    /**
     * 提示代码 0失败，1成功，-1无访问权限
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 如果此接口有返回数据，就会有data
     */
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    private Result setCode(int code){
        this.code = code;
        return this;
    }

    public Result setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }

    public static Result noAuth(){
        return new Result(codeNoAuth).setMsg(msgNoAuth);
    }

    public static Result fail(){
        return new Result(codeFailed).setMsg(msgFailed);
    }

    public static Result ok(){
        return new Result(codeSuccess).setMsg(msgSuccess);
    }

}
