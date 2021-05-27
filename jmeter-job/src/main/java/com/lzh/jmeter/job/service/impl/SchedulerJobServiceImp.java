package com.lzh.jmeter.job.service.impl;

import com.lzh.jmeter.commons.core.exception.TaskException;
import com.lzh.jmeter.job.domain.SchedulerJob;
import com.lzh.jmeter.job.exception.JobExceptionCode;
import com.lzh.jmeter.job.service.ISchedulerJobService;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SchedulerJobServiceImp implements ISchedulerJobService {

    private static final Logger log = LoggerFactory.getLogger(SchedulerJobServiceImp.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(SchedulerJob schedulerJob) throws TaskException {
        try {
            // 启动调度器，默认初始化的时候已经启动
//            scheduler.start();
            //构建job信息
            Class<? extends Job> jobClass = schedulerJob.getJobClass();
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(schedulerJob.getJobName(), schedulerJob.getGroupName()).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(schedulerJob.getJobName(), schedulerJob.getGroupName()).withSchedule(scheduleBuilder).build();
            //获得JobDataMap，写入数据
            if (schedulerJob.getParam() != null) {
                trigger.getJobDataMap().putAll(schedulerJob.getParam());
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建任务失败", e);
            throw new TaskException(JobExceptionCode.ADD_TASK_FAIL);
        }
    }

    @Override
    public void pauseJob(String jobName, String groupName) throws TaskException {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
            log.error("暂停任务失败", e);
            throw new TaskException(JobExceptionCode.PAUSE_TASK_FAIL);
        }
    }

    @Override
    public void resumeJob(String jobName, String groupName) throws TaskException {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
            log.error("恢复任务失败", e);
            throw new TaskException(JobExceptionCode.RESUME_TASK_FAIL);
        }
    }

    @Override
    public void runOnce(String jobName, String groupName) throws TaskException {
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName, groupName));
        } catch (SchedulerException e) {
            log.error("立即运行一次定时任务失败", e);
            throw new TaskException(JobExceptionCode.RESUME_TASK_FAIL);
        }
    }

    @Override
    public void updateJob(SchedulerJob schedulerJob) throws TaskException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getJobName(), schedulerJob.getGroupName());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (schedulerJob.getCronExpression() != null) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCronExpression());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            }
            //修改map
            if (schedulerJob.getParam() != null) {
                trigger.getJobDataMap().putAll(schedulerJob.getParam());
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            log.error("更新任务失败", e);
            throw new TaskException(JobExceptionCode.UPDATE_TASK_FAIL);
        }
    }

    @Override
    public void deleteJob(String jobName, String groupName) throws TaskException {
        try {
            //暂停、移除、删除
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, groupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, groupName));
            scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        } catch (Exception e) {
            log.error("删除任务失败", e);
            throw new TaskException(JobExceptionCode.DELETE_TASK_FAIL);
        }
    }

    @Override
    public void startAllJobs() throws TaskException {
        try {
            scheduler.start();
        } catch (Exception e) {
            log.error("开启所有的任务失败", e);
            throw new TaskException(JobExceptionCode.START_TASK_FAIL);
        }
    }

    @Override
    public void pauseAllJobs() throws TaskException {
        try {
            scheduler.pauseAll();
        } catch (Exception e) {
            log.error("暂停所有任务失败", e);
            throw new TaskException(JobExceptionCode.PAUSE_ALL_TASK_FAIL);
        }
    }

    @Override
    public void resumeAllJobs() throws TaskException {
        try {
            scheduler.resumeAll();
        } catch (Exception e) {
            log.error("恢复所有任务失败", e);
            throw new TaskException(JobExceptionCode.RESUME_ALL_TASK_FAIL);
        }
    }

    @Override
    public void shutdownAllJobs() throws TaskException {
        try {

            if (!scheduler.isShutdown()) {
                // 需谨慎操作关闭scheduler容器
                // scheduler生命周期结束，无法再 start() 启动scheduler
                scheduler.shutdown(true);
            }
        } catch (Exception e) {
            log.error("关闭所有的任务失败", e);
            throw new TaskException(JobExceptionCode.SHUTDOWN_ALL_TASK_FAIL);
        }
    }

    @Override
    public List<SchedulerJob> schedulerJobs() throws TaskException {
        try{
            List<SchedulerJob> quartzJobsVOList = new ArrayList<>();
            //再获取Scheduler下的所有group
            List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
            for (String groupName : triggerGroupNames) {
                //组装group的匹配，为了模糊获取所有的triggerKey或者jobKey
                GroupMatcher groupMatcher = GroupMatcher.groupEquals(groupName);
                //获取所有的triggerKey
                Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(groupMatcher);
                for (TriggerKey triggerKey : triggerKeySet) {
                    //通过triggerKey在scheduler中获取trigger对象
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    //获取trigger拥有的Job
                    JobKey jobKey = trigger.getJobKey();
                    JobDetailImpl jobDetail = (JobDetailImpl) scheduler.getJobDetail(jobKey);
                    //组装页面需要显示的数据
                    SchedulerJob schedulerJob = new SchedulerJob();
                    schedulerJob.setGroupName(groupName);
                    schedulerJob.setJobName(jobDetail.getName());
                    schedulerJob.setCronExpression(trigger.getCronExpression());
                    schedulerJob.setJobClass(jobDetail.getJobClass());
                    quartzJobsVOList.add(schedulerJob);
                }
            }
            return quartzJobsVOList;
        }catch (Exception e){
            log.error("获取所有定时任务列表失败", e);
            throw new TaskException(JobExceptionCode.GET_ALL_SCHEDULER_FAIL);
        }
    }
}
