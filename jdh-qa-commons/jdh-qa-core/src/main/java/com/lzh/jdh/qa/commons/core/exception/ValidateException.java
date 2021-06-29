package com.lzh.jdh.qa.commons.core.exception;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:07:35
 * Modify date: 2021-04-18:07:35
 */
public class ValidateException extends BaseBusinessException{

    public ValidateException() {
        super();
    }

    public ValidateException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ValidateException(Throwable arg0) {
        super(arg0);
    }

    public ValidateException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ValidateException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ValidateException(ExceptionEnumInterface exceptionEnumInterface) {
        super();
        this.errorCode = exceptionEnumInterface.getCode();
        this.message = exceptionEnumInterface.getMessage();
    }

    public ValidateException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}