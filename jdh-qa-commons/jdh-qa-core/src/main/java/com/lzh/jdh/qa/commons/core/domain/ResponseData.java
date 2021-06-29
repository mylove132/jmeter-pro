package com.lzh.jdh.qa.commons.core.domain;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:07:44
 * Modify date: 2021-04-18:07:44
 */
public class ResponseData<T> extends AbstractResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
