package com.lzh.jmeter.commons.core.domain;
import java.io.Serializable;

/**
 * 服务返回基类
 *
 */
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
