package com.lzh.jdh.qa.commons.core.domain;

import java.io.Serializable;

/**
 * 服务请求基类
 * @author liuzhanhui
 * @date 2021-3-1
 */
public abstract class AbstractRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    public abstract void requestCheck();
}
