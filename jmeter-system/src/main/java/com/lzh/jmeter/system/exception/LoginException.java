package com.lzh.jmeter.system.exception;

import com.lzh.jmeter.commons.core.exception.BaseException;

public class LoginException extends BaseException {

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
