package com.syc.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 检测超时订单的处理,这个只是一个DeMo而已
 * @author choxsu
 * @date 2018/11/27
 */
@Component
public class TimeOutRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("This will be execute when the project was started!");
    }
}
