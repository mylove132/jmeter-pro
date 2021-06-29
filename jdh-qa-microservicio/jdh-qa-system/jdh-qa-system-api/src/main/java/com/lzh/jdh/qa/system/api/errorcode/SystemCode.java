package com.lzh.jdh.qa.system.api.errorcode;

import com.lzh.jdh.qa.commons.core.exception.ExceptionEnumInterface;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:02
 * Modify date: 2021-04-18:08:02
 */
public enum SystemCode implements ExceptionEnumInterface {
    LOG_REQUISITE_PARAMETER_NOT_EXIST(1001, "日志请求必要的参数不能为空"),
    USER_REQUISITE_PARAMETER_NOT_EXIST(2001, "用户请求必要的参数不能为空"),
    ;
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误提示信息
     */
    private String message;

    SystemCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int code) {
        for (SystemCode value : values()) {
            if (value.code == code) {
                return value.message;
            }
        }
        return null;
    }
}