package com.lzh.jmeter.business.domain.rsponse;

import lombok.Data;

import java.io.Serializable;

@Data
public class UIScriptVO implements Serializable {

    private int id;

    private String name;

    private String theme;

    private String desc;

    private String code;

    private String lang;
}
