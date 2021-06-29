package com.lzh.jdh.qa.commons.log.service;
import com.lzh.jdh.qa.system.api.domain.SysOperLog;
import com.lzh.jdh.qa.system.api.service.ISysOperLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:15:40
 * Modify date: 2021-04-18:15:40
 */
@Service
@Slf4j
public class AsyncLogService {

    @DubboReference(timeout = 30000, async = true)
    private ISysOperLogService sysOperLogService;

    public void saveSysLog(SysOperLog sysOperLog)
    {
        sysOperLogService.saveSysOperLogService(sysOperLog);
    }
}
