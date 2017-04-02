package com.ztc.enums;

/**
 * Created by zt
 * 2017/4/2 16:59
 */
public enum ErrorEnum {

    UNKNOWN_ERROR(-1, "未知错误"),

    SUCCESS(0, "成功"),

    ERROR(1, "失败"),

    PRIMARY_SCHOOL(100, "还在上小学"),

    MIDDLE_SCHOOL(101, "还在上初中");

    private Integer code;

    private String msg;

    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
