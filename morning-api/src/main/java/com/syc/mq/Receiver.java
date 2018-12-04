package com.syc.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.service.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author choxsu
 * @date 2018/12/04
 */
@Component
@Slf4j
public class Receiver {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @RabbitListener(queues = RabbitConfig.name, containerFactory = RabbitConfig.pointTaskContainerFactory)
    public void receive(String message) {
        System.out.println("接受消息：" + message);
    }

    @RabbitListener(queues = {RabbitConfig.ORDER_QUEUE_NAME})
    public void orderDelayQueue(@Payload Orders orders, Message message, Channel channel) {
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]", sdf.format(new Date()), orders.getOrderSn());
        if (orders.getStatus() == 0) {
            orders.setStatus(2);
            log.info("【该[{}]订单未支付，取消订单】" , orders.getOrderSn());
        } else if (orders.getStatus() == 1) {
            log.info("【该订单已完成支付】");
        } else if (orders.getStatus() == 2) {
            log.info("【该订单已取消】");
        }
        log.info("###########################################");
    }
}
