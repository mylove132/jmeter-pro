package com.lzh.jmeter.business.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RunUIScriptVO implements Serializable {

    private LangEnum lang;

    private String code;
}

enum LangEnum {
    java, python, javascript
}
