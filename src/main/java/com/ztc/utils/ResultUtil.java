package com.ztc.utils;

import com.ztc.entity.Result;
import com.ztc.enums.ErrorEnum;

/**
 * Created by zt
 * 2017/4/2 16:14
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ErrorEnum.SUCCESS.getCode());
        result.setMsg(ErrorEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ErrorEnum errorEnum) {
        Result result = new Result();
        result.setCode(errorEnum.getCode());
        result.setMsg(errorEnum.getMsg());
        return result;
    }

}
