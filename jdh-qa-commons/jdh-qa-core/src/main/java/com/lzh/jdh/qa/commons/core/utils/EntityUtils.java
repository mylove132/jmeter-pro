package com.lzh.jdh.qa.commons.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class EntityUtils {

    public static <T> T covertClass (Object source, Class<T> target) {
        String acJson = JSON.toJSONString(source);
        JSONObject jsonObj = (JSONObject) JSON.parse(acJson);
        T targetClass = JSONObject.toJavaObject(jsonObj, target);
        return targetClass;
    }
}
