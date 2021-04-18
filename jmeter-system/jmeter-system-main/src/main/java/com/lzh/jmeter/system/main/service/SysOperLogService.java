package com.lzh.jmeter.system.main.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.system.api.domain.SysOperLog;
import com.lzh.jmeter.system.api.service.ISysOperLogService;
import com.lzh.jmeter.system.main.mapper.SysOperLogMapper;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:35
 * Modify date: 2021-04-18:08:35
 */
@DubboService(interfaceClass = ISysOperLogService.class)
public class SysOperLogService implements ISysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;

    public SysOperLogService(SysOperLogMapper sysOperLogMapper) {
        this.sysOperLogMapper = sysOperLogMapper;
    }

    @Override
    public ResponseData<Boolean> saveSysOperLogService(SysOperLog sysOperLog) throws BizException {
        sysOperLog.requestCheck();
        int res = sysOperLogMapper.insertOperlog(sysOperLog);
        return new ResponseUtil<Boolean>().success(res == 0);
    }
}
