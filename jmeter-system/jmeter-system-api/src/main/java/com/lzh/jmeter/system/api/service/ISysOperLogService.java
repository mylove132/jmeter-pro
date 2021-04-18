package com.lzh.jmeter.system.api.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.system.api.domain.SysOperLog;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:07
 * Modify date: 2021-04-18:08:07
 */
public interface ISysOperLogService {

    /**
     * 保存系统日志
     * @param sysOperLog
     * @return
     */
    ResponseData<Boolean> saveSysOperLogService (SysOperLog sysOperLog) throws BizException;
}
