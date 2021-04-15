package com.lzh.jmeter.system.controller;

import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.commons.core.web.controller.BaseController;
import com.lzh.jmeter.system.domain.SysOperLog;
import com.lzh.jmeter.system.service.ISysOperLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController extends BaseController {

    private final ISysOperLogService sysOperLogService;

    public LogController(ISysOperLogService sysOperLogService) {
        this.sysOperLogService = sysOperLogService;
    }

    @PostMapping("/log")
    public R addLogController (SysOperLog sysOperLog) {
        int result = sysOperLogService.insertOperlog(sysOperLog);
        return R.ok(result);
    }

}
