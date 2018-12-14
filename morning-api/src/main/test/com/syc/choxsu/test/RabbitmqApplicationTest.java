package com.syc.choxsu.test;

import com.syc.api.service.TestService;
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
    private TestService testService;

    class ThreadA extends Thread{

        private String key;

        public ThreadA(String key) {
            this.key=key;
        }

        @Override
        public void run() {
            testService.seckill(key);
        }
    }

    @Test
    public void st(){

        //for (int i = 0; i < 100; i++) {
            ThreadA threadA = new ThreadA("Chosu");
            threadA.start();
        //}
    }


}
