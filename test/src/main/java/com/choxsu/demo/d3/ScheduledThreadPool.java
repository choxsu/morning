package com.choxsu.demo.d3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author choxsu
 * @date 2018/11/27
 */
public class ScheduledThreadPool {
    private static ScheduledExecutorService pool = null;

    /*初始化线程池*/
    public static void init() {
        if (pool == null) {
            pool = Executors.newScheduledThreadPool(3);
        }
    }

    /*提交任务执行*/
    public static void execute(Runnable r) {
        init();
        pool.execute(r);
    }

    /*提交延迟任务执行,1000毫秒*/
    public static void executeDelay(Runnable r, long delay) {
        init();
        pool.schedule(r, delay, TimeUnit.MILLISECONDS);
    }

    /* 关闭线程池*/
    public static void unInit() {
        if (pool == null || pool.isShutdown()) return;
        pool.shutdownNow();
        pool = null;
    }


    //定时任务////////////////////////////////////////////////////////////////////
    /*该接口定义了线程的名字，用于管理，如判断是否存活，是否停止该线程等等*/
    public interface SRunnable extends Runnable {
        String getName();
    }


    public static void main(String[] args) {
        SRunnable st = new SRunnable() {
            String name = "T1";
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void run() {
                System.out.println("10s后执行了");
            }
        };

        ScheduledThreadPool.executeDelay(st, 10 * 1000);
        System.out.println(st.getName());
    }


}
