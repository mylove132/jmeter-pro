package com.lzh.jmeter.commons.core.exception;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:07:35
 * Modify date: 2021-04-18:07:35
 */
public class ProcessException extends BaseBusinessException {
    public ProcessException() {
        super();
    }

    public ProcessException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ProcessException(Throwable arg0) {
        super(arg0);
    }

    public ProcessException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ProcessException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ProcessException(ExceptionEnumInterface exceptionEnumInterface) {
        super();
        this.errorCode = exceptionEnumInterface.getCode();
        this.message = exceptionEnumInterface.getMessage();
    }

    public ProcessException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}

