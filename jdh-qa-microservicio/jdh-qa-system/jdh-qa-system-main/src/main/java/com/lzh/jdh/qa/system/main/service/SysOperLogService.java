package com.lzh.jdh.qa.system.main.service;

import com.alibaba.fastjson.JSON;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.commons.core.exception.BizException;
import com.lzh.jdh.qa.system.api.domain.SysOperLog;
import com.lzh.jdh.qa.system.api.service.ISysOperLogService;
import com.lzh.jdh.qa.system.main.mapper.SysOperLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:08:35
 * Modify date: 2021-04-18:08:35
 */
@Slf4j
@DubboService(interfaceClass = ISysOperLogService.class)
public class SysOperLogService implements ISysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;

    public SysOperLogService(SysOperLogMapper sysOperLogMapper) {
        this.sysOperLogMapper = sysOperLogMapper;
    }

    @Override
    public ResponseData<Boolean> saveSysOperLogService(SysOperLog sysOperLog) throws BizException {
        log.info("操作纪律请求:{}", JSON.toJSONString(sysOperLog));
        sysOperLog.requestCheck();
        int res = sysOperLogMapper.insertOperlog(sysOperLog);
        return new ResponseUtil<Boolean>().success(res == 0);
    }
}
