package com.lzh.jmeter.commons.mq.service;
import com.alibaba.fastjson.JSONObject;
import com.lzh.jmeter.commons.core.domain.BaseMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class RabbitProvider implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static Logger logger = LoggerFactory.getLogger(RabbitProvider.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init () {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("消息发送成功:" + correlationData);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.error("消息发送失败:" + new String(message.getBody()));
    }

    /**
     * jmeter服务端生产消息
     * @param baseMqMessage
     */
    public void send (BaseMqMessage baseMqMessage) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(baseMqMessage.getExchange(), baseMqMessage.getRoutingKey(), JSONObject.toJSON(baseMqMessage).toString(), correlationId);
    }
}

