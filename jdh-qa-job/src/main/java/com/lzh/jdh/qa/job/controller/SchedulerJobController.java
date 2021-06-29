package com.lzh.jdh.qa.job.controller;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.commons.core.exception.TaskException;
import com.lzh.jdh.qa.commons.core.web.controller.BaseController;
import com.lzh.jdh.qa.commons.log.annotation.Log;
import com.lzh.jdh.qa.commons.log.enums.BusinessType;
import com.lzh.jdh.qa.job.domain.SchedulerJob;
import com.lzh.jdh.qa.job.service.ISchedulerJobService;
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
    public ResponseData<Boolean> addSchedulerJobController (SchedulerJob schedulerJob) throws TaskException {
        schedulerJobService.addJob(schedulerJob);
        return new ResponseUtil().success(true);
    }

    @Log(title = "获取定时任务列表")
    @GetMapping("/job/list")
    public ResponseData<List<SchedulerJob>> getSchedulerJobListController () throws TaskException {
        startPage();
        List<SchedulerJob> schedulerJobs = schedulerJobService.schedulerJobs();
        return  new ResponseUtil().success(schedulerJobs);
    }

}
