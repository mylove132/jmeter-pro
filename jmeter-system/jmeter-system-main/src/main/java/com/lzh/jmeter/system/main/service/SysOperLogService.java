package com.lzh.jmeter.system.main.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.commons.core.utils.EntityUtils;
import com.lzh.jmeter.system.api.domain.request.SysOperLogRequest;
import com.lzh.jmeter.system.api.domain.response.SysOperLogResponse;
import com.lzh.jmeter.system.api.service.ISysOperLogService;
import com.lzh.jmeter.system.main.domain.SysOperLog;
import com.lzh.jmeter.system.main.mapper.SysOperLogMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:35
 * Modify date: 2021-04-18:08:35
 */
@Component
@DubboService(interfaceClass = ISysOperLogService.class)
public class SysOperLogService implements ISysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;

    public SysOperLogService(SysOperLogMapper sysOperLogMapper) {
        this.sysOperLogMapper = sysOperLogMapper;
    }

    @Override
    public ResponseData<SysOperLogResponse> saveSysOperLogService(SysOperLogRequest sysOperLogRequest) throws BizException {
        sysOperLogRequest.requestCheck();
        SysOperLog sysOperLog = EntityUtils.covertClass(sysOperLogRequest, SysOperLog.class);
        int res = sysOperLogMapper.insertOperlog(sysOperLog);
        SysOperLogResponse sysOperLogResponse = new SysOperLogResponse();
        return new ResponseUtil<SysOperLogResponse>().success(sysOperLogResponse);
    }
}
