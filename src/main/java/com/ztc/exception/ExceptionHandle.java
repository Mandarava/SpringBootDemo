package com.ztc.exception;

import com.ztc.entity.Result;
import com.ztc.enums.ErrorEnum;
import com.ztc.utils.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zt 2017/4/2 16:34
 */
@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(HttpServletRequest request, Exception e) {
        log.error("【系统异常】 {}", e.getMessage(), e);
        log.error(String.valueOf(request.getRequestURL()));
        return ResultUtil.error(ErrorEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException businessException) {
        log.error(businessException.getMessage(), businessException);
        return ResultUtil.error(businessException.getCode(), businessException.getMessage());
    }


}
