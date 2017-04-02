package com.ztc.exception;

import com.ztc.entity.Result;
import com.ztc.enums.ErrorEnum;
import com.ztc.utils.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zt
 * 2017/4/2 16:34
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(HttpServletRequest request, Exception e) {
        logger.error("【系统异常】 {}", e);
        logger.error(String.valueOf(request.getRequestURL()));
        return ResultUtil.error(ErrorEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandler(BusinessException businessException) {
        logger.error(businessException.getMessage(), businessException);
        return ResultUtil.error(businessException.getCode(), businessException.getMessage());
    }

}
