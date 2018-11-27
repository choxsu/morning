package com.choxsu.demo.d3;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
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
        map.clear();
        // pool.shutdown();
        pool.shutdownNow();
        pool = null;
    }


    //定时任务////////////////////////////////////////////////////////////////////
    /*该接口定义了线程的名字，用于管理，如判断是否存活，是否停止该线程等等*/
    public interface SRunnable extends Runnable {
        String getName();
    }

    private static HashMap<String, ScheduledFuture> map = new HashMap<>();

    /**
     * @param sr     需要执行测线程，该线程必须继承SRunnable
     * @param delay  延迟执行时间 1000毫秒
     * @param period 执行周期时间 1000毫秒
     */
    public static void stard(SRunnable sr, long delay, long period) {
        if (sr.getName() == null || map.get(sr.getName()) != null) {
            throw new UnsupportedOperationException("线程名不能为空或者线程名不能重复！");
        }
        if (pool == null || pool.isShutdown()) init();
        ScheduledFuture scheduledFuture = pool.scheduleAtFixedRate(sr, delay, period, TimeUnit.MILLISECONDS);
        map.put(sr.getName(), scheduledFuture);
    }

    /**
     * @param sr 停止当前正在执行的线程，该线程必须是继承SRunnable
     */
    public static void stop(SRunnable sr) {
        if (sr.getName() == null) {
            throw new UnsupportedOperationException("停止线程时，线程名不能为空！");
        }
        if (pool == null || pool.isShutdown()) return;//服务未启动
        if (map.size() > 0 && map.get(sr.getName()) != null) {
            map.get(sr.getName()).cancel(true);
            map.remove(sr.getName());
        }
        if (map.size() <= 0) {
            unInit();
        }
    }

    /**
     * 判断该线程是否还存活着，还在运行
     *
     * @param sr
     * @return
     */
    public static boolean isAlive(SRunnable sr) {
        if (map.size() > 0 && map.get(sr.getName()) != null) {
            return !map.get(sr.getName()).isDone();
        }
        return false;
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
