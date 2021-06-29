package com.lzh.jdh.qa.commons.core.constant;

/**
 * mq常量定义
 *
 */
public class RabbitmqConstants
{
    /**
     * jmeter日志队列
     */
    public static final String JMETER_INFO_QUEUE = "JMETER_INFO_QUEUE";

    /**
     * 运行jmeter成功队列
     */
    public static final String RUN_JMETER_SCRIPT_SUCCESS_QUEUE = "RUN_JMETER_SCRIPT_SUCCESS_QUEUE";

    /**
     * 运行ui脚本完成事件
     */
    public static final String RUN_UI_SCRIPT_FINISH_QUEUE = "RUN_UI_SCRIPT_FINISH_QUEUE";
    /**
     * 运行jmeter失败队列
     */
    public static final String RUN_JMETER_SCRIPT_FAIL_QUEUE = "RUN_JMETER_SCRIPT_FAIL_QUEUE";

    /**
     * jmeter交换机名称
     */
    public final static String JMETER_EXCHANGE = "JMETER_EXCHANGE";

    /**
     * ui交换机名称
     */
    public final static String UI_EXCHANGE = "UI_EXCHANGE";

    /**
     * jmeter路由值
     */
    public static final String RK_JMETER = "RK_JMETER";

    /**
     * UI路由值
     */
    public static final String RK_UI = "RK_UI";
}
