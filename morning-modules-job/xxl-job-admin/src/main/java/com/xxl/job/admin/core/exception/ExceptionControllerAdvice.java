package com.xxl.job.admin.core.exception;


import com.xxl.job.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description: 处理所有异常
 * @author: suxiaoqiu
 * @createTime: 2022-03-27 10:14
 **/

@RestControllerAdvice(basePackages = {"com.xxl.job.admin.controller"})
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * 参数非法（效验参数）异常 MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnT<Object> handleValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
        return new ReturnT<>(500, "请求参数格式错误");
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ReturnT<Object> handleException(MissingServletRequestParameterException e) {
        logger.error(e.getMessage());
        return new ReturnT<>(500, "参数【" + e.getParameterName() + "】必须传递");
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ReturnT<Object> handleException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return new ReturnT<>(500, "请求参数格式错误");
    }


    @ExceptionHandler(value = XxlJobException.class)
    public ReturnT<Object> handleException(XxlJobException e) {
        return new ReturnT<>(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = Throwable.class)
    public ReturnT<Object> handleException(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new ReturnT<>(500, "系统异常，请重试！");
    }
}
