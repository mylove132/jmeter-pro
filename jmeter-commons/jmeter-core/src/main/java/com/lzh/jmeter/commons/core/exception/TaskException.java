package com.lzh.jmeter.commons.core.exception;

public class TaskException extends BaseBusinessException {

    public TaskException(ExceptionEnumInterface exceptionEnumInterface) {
        super();
        this.errorCode = exceptionEnumInterface.getCode();
        this.message = exceptionEnumInterface.getMessage();
    }
}
