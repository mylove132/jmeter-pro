package com.lzh.jdh.qa.commons.mq.config;

import com.lzh.jdh.qa.commons.core.constant.RabbitmqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq 队列，交换机,路由配置
 */
@Configuration
public class RabbitConfig {

    // jmeter日志队列名称
    @Bean
    public Queue jmeterInfoQueue() {
        return new Queue(RabbitmqConstants.JMETER_INFO_QUEUE, true);
    }

    /**
     * 运行jmeter脚本失败队列
     * @return
     */
    @Bean
    public Queue runJmeterScriptFailQueue() {
        return new Queue(RabbitmqConstants.RUN_JMETER_SCRIPT_FAIL_QUEUE, true);
    }

    /**
     * 运行jmeter脚本成功队列
     * @return
     */
    @Bean
    public Queue runJmeterScriptSuccessQueue() {
        return new Queue(RabbitmqConstants.RUN_JMETER_SCRIPT_SUCCESS_QUEUE, true);
    }

    /**
     * 运行ui脚本完成事件
     * @return
     */
    @Bean
    public Queue runUIScriptFinishQueue() {
        return new Queue(RabbitmqConstants.RUN_JMETER_SCRIPT_SUCCESS_QUEUE, true);
    }


    // jmeter交换机名称
    @Bean
    DirectExchange jmeterDirectExchange() {
        return new DirectExchange(RabbitmqConstants.JMETER_EXCHANGE, true, false);
    }

    // ui交换机名称
    @Bean
    DirectExchange uiDirectExchange() {
        return new DirectExchange(RabbitmqConstants.UI_EXCHANGE, true, false);
    }

    //绑定jmeter日志路由
    @Bean
    Binding bindingJmeterInfoDirect() {
        return BindingBuilder.bind(jmeterInfoQueue()).to(jmeterDirectExchange()).with(RabbitmqConstants.RK_JMETER);
    }

    //绑定jmeter运行成功路由
    @Bean
    Binding bindingRunJmeterScriptSuccessDirect() {
        return BindingBuilder.bind(runJmeterScriptSuccessQueue()).to(jmeterDirectExchange()).with(RabbitmqConstants.RK_JMETER);
    }

    //绑定ui运行完成路由
    @Bean
    Binding bindingRunUIScriptSuccessDirect() {
        return BindingBuilder.bind(runUIScriptFinishQueue()).to(uiDirectExchange()).with(RabbitmqConstants.RK_UI);
    }

    //绑定jmeter运行失败路由
    @Bean
    Binding bindingRunJmeterScriptFailDirect() {
        return BindingBuilder.bind(runJmeterScriptFailQueue()).to(jmeterDirectExchange()).with(RabbitmqConstants.RK_JMETER);
    }

}
