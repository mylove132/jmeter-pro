package com.lzh.jmeter.system.service.impl;

import com.lzh.jmeter.system.domain.JmeterScript;
import com.lzh.jmeter.system.mapper.JmeterScriptMapper;
import com.lzh.jmeter.system.service.IJmeterScriptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JmeterScriptServiceImp implements IJmeterScriptService {

    private final JmeterScriptMapper jmeterScriptMapper;

    public JmeterScriptServiceImp(JmeterScriptMapper jmeterScriptMapper) {
        this.jmeterScriptMapper = jmeterScriptMapper;
    }

    @Override
    public JmeterScript queryScriptByIdService(Long scriptId) {
        return jmeterScriptMapper.queryScriptById(scriptId);
    }

    @Override
    public List<JmeterScript> queryScriptsService() {
        return jmeterScriptMapper.queryScripts();
    }

    @Override
    public void delScriptByIdService(Long scriptId) {
        jmeterScriptMapper.delScriptById(scriptId);
    }

    @Override
    public void addScriptService(JmeterScript script) {
        jmeterScriptMapper.addScript(script);
    }
}
