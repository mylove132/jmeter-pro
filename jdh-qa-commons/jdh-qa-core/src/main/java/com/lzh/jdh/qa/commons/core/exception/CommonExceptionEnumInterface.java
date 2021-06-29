package com.lzh.jdh.qa.commons.core.exception;

import com.lzh.jdh.qa.commons.core.constant.Constants;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:11:51
 * Modify date: 2021-04-18:11:51
 */
public enum  CommonExceptionEnumInterface implements ExceptionEnumInterface
{
    SUCCESS(Constants.SUCCESS, "OK"),
    FAIL(Constants.FAIL,"error"),
    PARAM_NOT_NULL(10000, "参数不能为空"),
    UTIL_SQL_VALIDATE_EXCEPTION(10001, "参数不符合规范，不能进行查询"),
    NOT_AUTHORITIES(50000, "NOT AUTH"),
    AUTHORITIES_FAIL(50001, "AUTH FAIL"),
    AUTHORITIES_EXPIRED(50002, "AUTH EXPIRED"),
    LOGIN_FAIL(50003, "LOGIN FAIL"),;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误提示信息
     */
    private String message;

    CommonExceptionEnumInterface(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
