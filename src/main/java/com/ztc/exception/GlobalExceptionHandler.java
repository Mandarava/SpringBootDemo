package com.ztc.exception;

import com.ztc.entity.ErrorInfo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zt on 2017/2/3.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", e);
        model.addObject("url", req.getRequestURL());
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, BusinessException e) throws Exception {
        ErrorInfo<String> result = new ErrorInfo<>();
        result.setMessage(e.getMessage());
        result.setCode(ErrorInfo.ERROR);
        result.setData("Some Data");
        result.setUrl(req.getRequestURL().toString());
        return result;
    }

}
