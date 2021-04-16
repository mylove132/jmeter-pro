package com.lzh.jmeter.job.controller;

import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.commons.core.exception.job.TaskException;
import com.lzh.jmeter.commons.core.web.controller.BaseController;
import com.lzh.jmeter.commons.log.annotation.Log;
import com.lzh.jmeter.commons.log.enums.BusinessType;
import com.lzh.jmeter.job.domain.SchedulerJob;
import com.lzh.jmeter.job.service.ISchedulerJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulerJobController extends BaseController {


    private final ISchedulerJobService schedulerJobService;

    public SchedulerJobController(ISchedulerJobService schedulerJobService) {
        this.schedulerJobService = schedulerJobService;
    }

    @Log(title = "添加定时任务", businessType = BusinessType.INSERT)
    @PostMapping("/job")
    public R<Boolean> addSchedulerJobController (SchedulerJob schedulerJob) throws TaskException {
        schedulerJobService.addJob(schedulerJob);
        return R.ok(true);
    }

    @Log(title = "获取定时任务列表")
    @GetMapping("/job/list")
    public R<List<SchedulerJob>> getSchedulerJobListController () throws TaskException {
        startPage();
        List<SchedulerJob> schedulerJobs = schedulerJobService.schedulerJobs();
        return R.ok(schedulerJobs);
    }

}
