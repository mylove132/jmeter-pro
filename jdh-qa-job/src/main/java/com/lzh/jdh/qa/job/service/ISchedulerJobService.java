package com.lzh.jdh.qa.job.service;

import com.lzh.jdh.qa.commons.core.exception.TaskException;
import com.lzh.jdh.qa.job.domain.SchedulerJob;

import java.util.List;

public interface ISchedulerJobService {

    /**
     * 添加任务可以传参数
     * @param schedulerJob
     */
    void addJob(SchedulerJob schedulerJob) throws TaskException;

    /**
     * 暂停任务
     * @param jobName
     * @param groupName
     */
    void pauseJob(String jobName, String groupName) throws TaskException;

    /**
     * 恢复任务
     * @param jobName
     * @param groupName
     */
    void resumeJob(String jobName, String groupName) throws TaskException;

    /**
     * 立即运行一次定时任务
     * @param jobName
     * @param groupName
     */
    void runOnce(String jobName, String groupName) throws TaskException;

    /**
     * 更新任务
     * @param schedulerJob
     */
    void updateJob(SchedulerJob schedulerJob) throws TaskException;

    /**
     * 删除任务
     * @param jobName
     * @param groupName
     */
    void deleteJob(String jobName, String groupName) throws TaskException;

    /**
     * 启动所有任务
     */
    void startAllJobs() throws TaskException;

    /**
     * 暂停所有任务
     */
    void pauseAllJobs() throws TaskException;

    /**
     * 恢复所有任务
     */
    void resumeAllJobs() throws TaskException;

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs() throws TaskException;

    /**
     * 定时任务列表
     * @return
     */
    List<SchedulerJob> schedulerJobs ()  throws TaskException;;

}
