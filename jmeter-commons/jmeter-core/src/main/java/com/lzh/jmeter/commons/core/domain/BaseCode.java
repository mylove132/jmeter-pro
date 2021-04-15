package com.lzh.jmeter.commons.core.domain;

/**
 * 定义通用错误码
 * @author liuzhanhui
 * @date 2021-03-01 17:38 2021-03-01 17:46
 */
public interface BaseCode {

    /**
     * 通用返回code码
     * @return
     */
    Integer getCode();

    /**
     * 通用返回消息
     * @return
     */
    String getMsg();
}
