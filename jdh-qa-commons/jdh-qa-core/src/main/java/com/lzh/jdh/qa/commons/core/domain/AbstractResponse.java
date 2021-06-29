package com.lzh.jdh.qa.commons.core.domain;
import java.io.Serializable;

/**
 * 服务返回基类
 *
 */
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
