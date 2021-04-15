package com.lzh.jmeter.commons.log.service;
import com.lzh.jmeter.system.domain.SysOperLog;
import com.lzh.jmeter.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 *
 */
@Service
public class AsyncLogService
{
    @Autowired
    private ISysOperLogService sysOperLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        sysOperLogService.insertOperlog(sysOperLog);
    }
}
