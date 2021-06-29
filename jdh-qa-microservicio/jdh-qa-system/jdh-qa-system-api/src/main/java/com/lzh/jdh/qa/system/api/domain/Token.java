package com.lzh.jdh.qa.system.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class Token implements Serializable {

    private String token;

    private String expireTime;

    private long userId;

    private String userName;

    private Set<String> permissions;

    private Set<String> roles;
}
