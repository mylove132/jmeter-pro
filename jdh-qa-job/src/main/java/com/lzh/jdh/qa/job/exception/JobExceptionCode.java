package com.lzh.jdh.qa.job.exception;

import com.lzh.jdh.qa.commons.core.exception.ExceptionEnumInterface;

public enum JobExceptionCode implements ExceptionEnumInterface {
    ADD_TASK_FAIL(60001, "添加定时任务失败"),
    PAUSE_TASK_FAIL(60002, "暂停定时任务失败"),
    RESUME_TASK_FAIL(60003, "恢复定时任务失败"),
    UPDATE_TASK_FAIL(60004, "更新定时任务失败"),
    DELETE_TASK_FAIL(60005, "删除定时任务失败"),
    START_TASK_FAIL(60006, "开启定时任务失败"),
    PAUSE_ALL_TASK_FAIL(60007, "暂停所有定时任务失败"),
    RESUME_ALL_TASK_FAIL(60008, "恢复所有定时任务失败"),
    SHUTDOWN_ALL_TASK_FAIL(60009, "关闭所有定时任务失败"),
    GET_ALL_SCHEDULER_FAIL(60010, "获取所有定时任务失败"),
    ;

    private int code;

    private String message;

    private JobExceptionCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
