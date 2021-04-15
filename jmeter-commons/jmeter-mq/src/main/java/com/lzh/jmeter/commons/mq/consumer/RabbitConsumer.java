package com.lzh.jmeter.commons.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jmeter.commons.core.constant.RabbitmqConstants;
import com.lzh.jmeter.commons.core.domain.BaseMqMessage;
import com.lzh.jmeter.commons.core.utils.SecurityUtils;
import com.lzh.jmeter.commons.websocket.utils.LocalSession;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

/**
 * jmeter mq消费端
 */
@Component
public class RabbitConsumer {

    private static Logger log = LoggerFactory.getLogger(RabbitConsumer.class);

    /**
     * 发送jmeter日志信息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitmqConstants.JMETER_INFO_QUEUE)
    @RabbitHandler
    public void sendJmeterMessageToWs (Message message, Channel channel) throws IOException {
        try {
            BaseMqMessage baseMqMessage = JSONObject.parseObject(new String(message.getBody()), BaseMqMessage.class);
            LocalSession.sendMessageToAll(baseMqMessage.getContents());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认消息成功消费
        }catch (IOException e) {
            log.error("消息推送前台出错：" + e.getMessage() + "/r/n重新发送");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); //重新发送
        }
    }


    /**
     * 发送jmeter脚本执行完成后的消息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitmqConstants.RUN_JMETER_SCRIPT_SUCCESS_QUEUE)
    @RabbitHandler
    public void sendRunSuccessMessageToWs (Message message, Channel channel) throws IOException {
        try {
            BaseMqMessage baseMqMessage = JSONObject.parseObject(new String(message.getBody()), BaseMqMessage.class);
            Session session = LocalSession.getLocalSession(String.valueOf(SecurityUtils.getUserId()));
            LocalSession.sendMessage(session, baseMqMessage);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认消息成功消费
        }catch (IOException e) {
            log.error("消息推送前台出错：" + e.getMessage() + "/r/n重新发送");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); //重新发送
        }
    }
}
