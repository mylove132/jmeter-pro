package com.lzh.jmeter.business.exception;

import com.lzh.jmeter.commons.core.exception.ExceptionEnumInterface;

public enum UIScriptExceptionEnum implements ExceptionEnumInterface {

    COMPILER_FAIL(30001, "脚本编译失败"),
    RUN_CODE_FAIL(30002, "执行代码失败");

    private int code;

    private String message;

    UIScriptExceptionEnum (int code, String message) {
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
