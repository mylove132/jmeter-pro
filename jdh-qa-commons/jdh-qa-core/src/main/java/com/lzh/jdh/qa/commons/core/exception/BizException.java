package com.lzh.jdh.qa.commons.core.exception;

/**
 * @Author: liuzhanhui
 * @Decription: 业务层异常处理
 * @Date: Created in 2021-04-18:07:32
 * Modify date: 2021-04-18:07:32
 */
public class BizException extends BaseBusinessException {

    public BizException() {
        super();
    }

    public BizException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BizException(Throwable arg0) {
        super(arg0);
    }

    public BizException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public BizException(ExceptionEnumInterface exceptionEnumInterface) {
        super();
        this.errorCode = exceptionEnumInterface.getCode();
        this.message = exceptionEnumInterface.getMessage();
    }

    public BizException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}