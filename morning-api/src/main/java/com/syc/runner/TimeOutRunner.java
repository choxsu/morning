package com.syc.runner;

import com.syc.model.result.Result;
import com.syc.service.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 检测超时订单的处理,这个只是一个DeMo而已
 * 也可以 implements CommandLineRunner
 *
 * @author choxsu
 * @date 2018/11/27
 */
@Slf4j
@Component
public class TimeOutRunner implements ApplicationRunner {

    @Autowired
    private OrdersService ordersService;

    /**
     * 当后台系统重启时，要去遍历一次数据库，并将已经超时的订单处理，并将未付款订单加入队列中去
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) {
        try {
            Result result = ordersService.orderTimeoutAction(args);
            if (result == null) {
                return;
            }
            log.info("\t\tres|info:msg={}", result.getMsg());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
