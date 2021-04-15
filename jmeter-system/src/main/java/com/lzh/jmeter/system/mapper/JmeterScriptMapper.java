package com.lzh.jmeter.system.mapper;
import com.lzh.jmeter.system.domain.JmeterScript;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JmeterScriptMapper {

    JmeterScript queryScriptById (Long scriptId);

    List<JmeterScript> queryScripts ();

    void delScriptById (Long scriptId);

    void addScript (JmeterScript script);
}
