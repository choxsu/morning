package com.syc.choxsu.test;

import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.mq.Sender;
import com.syc.runner.TimeOutRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author choxsu
 * @date 2018/12/04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTest {

    @Autowired
    private Sender sender;

    @Test
    public void hello(){
        sender.sender();
        Orders orders = new Orders();
        orders.setStatus(0);
        sender.sendDelay(orders);
    }

}
