package com.syc.mq;

import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.service.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author choxsu
 * @date 2018/12/04
 */
@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public void sender() {
        amqpTemplate.convertAndSend(RabbitConfig.name, "你好，rabbitmq");
    }

    public void sendDelay(Orders order) {

        log.info("【订单生成时间】" + sdf.format(new Date()) + "【10s后检查订单是否已经支付】" + order.getOrderSn());
        this.amqpTemplate.convertAndSend(RabbitConfig.ORDER_DELAY_EXCHANGE, RabbitConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明
            // Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration((1 * 10 * 1000) + "");
            return message;
        });
    }

}
