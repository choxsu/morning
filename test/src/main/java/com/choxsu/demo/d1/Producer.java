package com.choxsu.demo.d1;

import java.util.Vector;

/**
 * @author choxsu
 * @date 2018/11/6 0006
 */
public class Producer implements Runnable {

    private final Vector sharedQueue;
    private final int SIZE;

    public Producer(Vector sharedQueue, int SIZE) {
        this.sharedQueue = sharedQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        //for (int i = 0; i < 100; i++) {
        int i = 1;
        while (true) {
            System.out.println("Producer:" + i);
            try {
                produce(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Producer.class.getName());
            }
            i++;
        }
        //}
    }

    private void produce(int i) throws InterruptedException {
        //wait if queue is full
        while (sharedQueue.size() == SIZE) {
            synchronized (sharedQueue) {
                System.out.println("Queue is full " + Thread.currentThread().getName()
                        + " is waiting , size: " + sharedQueue.size());
                sharedQueue.wait();
            }
        }
        Thread.sleep(500);
        //producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(i);
            sharedQueue.notifyAll();
        }
    }
}
