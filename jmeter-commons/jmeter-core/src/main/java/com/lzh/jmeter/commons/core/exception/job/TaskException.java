package com.lzh.jmeter.commons.core.exception.job;

/**
 * 计划策略异常
 *
 */
public class TaskException extends Exception
{
    private static final long serialVersionUID = 1L;

    private int code;

    public TaskException(String msg, int code)
    {
        this(msg, code, null);
    }

    public TaskException(String msg, int code, Exception nestedEx)
    {
        super(msg, nestedEx);
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public enum Code
    {
        ADD_TASK_FAIL(1001),
        PAUSE_TASK_FAIL(1002),
        PAUSE_ALL_TASK_FAIL(1003),
        RESUME_TASK_FAIL(1004),
        RESUME_ALL_TASK_FAIL(1005),
        RUN_TASK_FAIL(1006),
        UPDATE_TASK_FAIL(1007),
        DELETE_TASK_FAIL(1008),
        START_TASK_FAIL(1009),
        SHUTDOWN_ALL_TASK_FAIL(1010),
        GET_ALL_SCHEDULER_FAIL(1011)
        ;
        private int code;


        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}