/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.common.util;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

import java.io.Serializable;

/**
 * @author choxsu
 * @date 2021/3/4
 * @describe
 */
@Data
public class ResultModel implements Serializable {

    public static final Integer SUCCESS = 1;//成功
    public static final Integer FAILED = 0;//失败

    public static final String SUCCESS_MSG = "成功";
    public static final String FAILED_MSG = "失败";


    private Object data;
    private String msg;
    private Integer code;
    private boolean isSuccess;

    public ResultModel() {}


    public ResultModel(Integer code) {
        this.code = code;
        this.isSuccess = SUCCESS.equals(code);
    }


    public ResultModel(Integer code, String msg, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.isSuccess = SUCCESS.equals(code);
    }

    public static ResultModel success() {
        return new ResultModel(SUCCESS, SUCCESS_MSG, null);
    }

    public static ResultModel success(String msg) {
        return new ResultModel(SUCCESS, msg, null);
    }

    public static ResultModel success(String msg, Object data) {
        return new ResultModel(SUCCESS, msg, data);
    }

    public static ResultModel failed() {
        return new ResultModel(FAILED, FAILED_MSG, null);
    }

    public static ResultModel failed(String msg) {
        return new ResultModel(FAILED, msg, null);
    }

    public static ResultModel failed(String msg, Object data) {
        return new ResultModel(FAILED, msg, data);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
