package com.ztc.exception;

import com.alibaba.fastjson.JSONObject;
import com.ztc.entity.Result;
import com.ztc.enums.ErrorEnum;
import com.ztc.utils.ResultUtil;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * ConstraintViolationException	违反约束，javax扩展定义 BindException	绑定失败，如表单对象参数违反约束
 * MethodArgumentNotValidException	参数无效，如JSON请求参数违反约束 MissingServletRequestParameterException	参数缺失
 * TypeMismatchException	参数类型不匹配
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> fieldErrors = processFieldErrors(result.getFieldErrors());
        return ResultUtil.error(ErrorEnum.ERROR.getCode(), JSONObject.toJSONString(fieldErrors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> fieldErrors = processFieldErrors(result.getFieldErrors());
        return ResultUtil.error(ErrorEnum.ERROR.getCode(), JSONObject.toJSONString(fieldErrors));
    }

    private List<String> processFieldErrors(List<FieldError> fieldErrors) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            FieldError error = new FieldError(
                    fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue()
                    , fieldError.isBindingFailure(), null, null
                    , fieldError.getDefaultMessage());
            errors.add(error.toString());
        }
        return errors;
    }

}
