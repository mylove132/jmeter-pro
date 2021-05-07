package com.lzh.jmeter.business.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

    private String username;

    private String password;
}
