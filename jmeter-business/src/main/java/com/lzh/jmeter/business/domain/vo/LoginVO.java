package com.lzh.jmeter.business.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

    private String username;

    private String password;
}
