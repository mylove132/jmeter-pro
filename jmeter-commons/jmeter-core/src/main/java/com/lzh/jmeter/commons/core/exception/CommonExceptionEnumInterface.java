package com.lzh.jmeter.commons.core.exception;

import com.lzh.jmeter.commons.core.constant.Constants;

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
    UTIL_SQL_VALIDATE_EXCEPTION(10001, "参数不符合规范，不能进行查询"),
    NOT_AUTHORITIES(50000, "没有认证"),
    LOGIN_FAIL(50001, "登录失败"),;

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
