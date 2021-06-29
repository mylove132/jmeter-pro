package com.lzh.jdh.qa.commons.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jdh.qa.commons.core.constant.RabbitmqConstants;
import com.lzh.jdh.qa.commons.core.domain.BaseMqMessage;
import com.lzh.jdh.qa.commons.websocket.utils.LocalSession;
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
    public void sendMessageToAll (Message message, Channel channel) throws IOException {
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
     * 发送UI脚本执行完成的消息事件
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = RabbitmqConstants.RUN_UI_SCRIPT_FINISH_QUEUE)
    @RabbitHandler
    public void sendUIScriptFinishMessageToWS (Message message, Channel channel) throws IOException {
        try {
            BaseMqMessage baseMqMessage = JSONObject.parseObject(new String(message.getBody()), BaseMqMessage.class);
            Session session = LocalSession.getLocalSession(String.valueOf(baseMqMessage.getUserId()));
            LocalSession.sendMessage(session, baseMqMessage);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认消息成功消费
        }catch (IOException e) {
            log.error("消息推送前台出错：" + e.getMessage() + "/r/n重新发送");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); //重新发送
        }
    }
}
