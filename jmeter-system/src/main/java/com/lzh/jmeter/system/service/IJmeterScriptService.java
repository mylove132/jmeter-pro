package com.lzh.jmeter.system.service;


import com.lzh.jmeter.system.domain.JmeterScript;

import java.util.List;

public interface IJmeterScriptService {

    JmeterScript queryScriptByIdService (Long scriptId);

    List<JmeterScript> queryScriptsService ();

    void delScriptByIdService (Long scriptId);

    void addScriptService (JmeterScript script);
}
