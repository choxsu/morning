package com.choxsu.demo.d3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author choxsu
 * @date 2018/11/27
 */
public class DelayTest implements Delayed {

    private int _expireInSecond;

    public DelayTest(int delaySecond) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, delaySecond);
        _expireInSecond = (int) (cal.getTimeInMillis() / 1000);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Calendar cal = Calendar.getInstance();
        return _expireInSecond - (cal.getTimeInMillis() / 1000);
    }

    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    /*public static void main(String[] args) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //定义延迟队列
        DelayQueue<DelayTest> delayQueue = new DelayQueue<>();

        //定义三个延迟任务
        DelayTest task1 = new DelayTest(10);
        DelayTest task2 = new DelayTest(5);
        DelayTest task3 = new DelayTest(15);

        delayQueue.add(task1);
        delayQueue.add(task2);
        delayQueue.add(task3);

        System.out.println(sdf.format(new Date()) + " start");

        while (delayQueue.size() != 0) {

            //如果没到时间，该方法会返回
            DelayTest task = delayQueue.poll();

            if (task != null) {
                Date now = new Date();
                System.out.println(sdf.format(now));
            }

            Thread.sleep(1000);
        }

    }*/

    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);

    public static void main(String[] args) {

        //下单成功后就将订单超时时间放入队列中，当支付成功后传入订单id，调用取消订单超时

        //当后台系统重启时，要去遍历一次数据库，并将已经超时的订单处理，并将未付款订单加入队列中去
        //OrderTimeOutRunnable outRunnable = new OrderTimeOutRunnable("T1");
        //遍历未付款的订单记录list，然后将时间作为
        int c = 0;
        for (int i = 20; i > 0; i--) {
            //算出剩余订单超时时间秒数
            int time = i;
            executor.schedule(new OrderTimeOutRunnable(i + ""), time, TimeUnit.SECONDS);
            if (time == 19)
                c = time;
        }
        System.out.println("队列装载完毕,c:" + c);
    }

    public interface SRunnable extends Runnable {
        String getName();
    }

    public static class OrderTimeOutRunnable implements SRunnable {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private String name;

        public OrderTimeOutRunnable(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void run() {
            //需要先判断订单是否已经支付，并且订单必须是未付款，并且时间已经大于超时时间了。

            //这里就需要去更改订单的状态去取消,
            System.out.println("time:" + name);
            System.out.println("Work start, thread id:" + Thread.currentThread().getId() + " " + sdf.format(new Date()));
        }
    }


}
