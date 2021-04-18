package com.lzh.jmeter.commons.log.service;
import com.lzh.jmeter.system.api.domain.SysOperLog;
import com.lzh.jmeter.system.api.service.ISysOperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:15:40
 * Modify date: 2021-04-18:15:40
 */
@Service
public class AsyncLogService {

    private final ISysOperLogService sysOperLogService;

    public AsyncLogService(ISysOperLogService sysOperLogService) {
        this.sysOperLogService = sysOperLogService;
    }

    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        sysOperLogService.saveSysOperLogService(sysOperLog);
    }
}
