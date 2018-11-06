package com.choxsu.demo.d1;

import java.util.Vector;

/**
 * 用 wait-notify 写一段代码来解决生产者-消费者问题
 * @author choxsu
 * @date 2018/11/6 0006
 */
public class Test {
    public static void main(String[] args) {
        Vector sharedQueue = new Vector();
        int size = 100;
        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedQueue, size), "Consumer");
        prodThread.start();
        consThread.start();
    }
}
