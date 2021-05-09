package com.syc.model.result;

import lombok.Getter;

@Getter
public class Result implements java.io.Serializable {

    private static final String msgSuccess = "ok";
    private static final String msgFailed = "fail";
    private static final String msgNoAuth = "No authority";

    private static final int codeNoAuth = -1;
    private static final int codeFailed = 0;
    private static final int codeSuccess = 1;



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

    /**
     * 是否成功
     */
    private boolean isSuccess;

    public Result() {

    }

    public Result(int code) {
        this.code = code;
        this.isSuccess = (this.code == codeSuccess) ;
    }

    private Result setCode(int code){
        this.code = code;
        this.isSuccess = (this.code == codeSuccess) ;
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

    public boolean isSuccess(){
        return this.isSuccess;
    }

    public static Result noAuth(){
        return new Result(codeNoAuth).setMsg(msgNoAuth);
    }

    public static Result fail(){
        return new Result(codeFailed).setMsg(msgFailed);
    }

    public static Result fail(String msg){
        return new Result(codeFailed).setMsg(msg);
    }

    public static Result fail(String msg, Object data){
        return new Result(codeFailed).setMsg(msg).setData(data);
    }


    public static Result ok(){
        return new Result(codeSuccess).setMsg(msgSuccess);
    }

    public static Result ok(String msg){
        return new Result(codeSuccess).setMsg(msg);
    }

    public static Result ok(String msg, Object data){
        return new Result(codeSuccess).setMsg(msg).setData(data);
    }


}
