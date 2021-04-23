package com.lzh.jmeter.business.domain.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class TokenVO implements Serializable {

    private String token;

    private String expireTime;

    private long userId;

    private String userName;

}
