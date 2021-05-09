/*
  Copyright © 2020 choxsu
  https://choxsu.cn
  All rights reserved.
 */
package com.syc.config;

import com.syc.common.util.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author choxsu
 * @date 2021/3/6
 * @describe
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    /**
     * 用来处理bean validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultModel resolveConstraintViolationException(ConstraintViolationException ex) {
        ResultModel errorResult = new ResultModel(ResultModel.FAILED);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            errorResult.setMsg(errorMessage);
            return errorResult;
        }
        errorResult.setMsg(ex.getMessage());
        return errorResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultModel resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ResultModel errorResult = new ResultModel(ResultModel.FAILED);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            errorResult.setMsg(errorMessage);
            return errorResult;
        }
        errorResult.setMsg(ex.getMessage());
        return errorResult;
    }

    @ExceptionHandler(Exception.class)
    public ResultModel serveException(Exception ex) {
        log.error(ex.getMessage());
        ResultModel errorResult = new ResultModel(ResultModel.FAILED);
        errorResult.setMsg("服务器异常，请稍后再试！");
        return errorResult;
    }


}
