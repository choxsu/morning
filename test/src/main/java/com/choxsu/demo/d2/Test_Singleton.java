package com.choxsu.demo.d2;

import org.junit.Test;

import java.util.*;

/**
 * 这里面一步一步教你创建一个线程安全的 Java 单例类。
 * 当我们说线程安全时，意思是即使初始化是在多线程环境中，
 * 仍然能保证单个实例。Java 中，使用枚举作为单例类是最简单的方式来创建线程安全单例模式的方式。
 * @author choxsu
 * @date 2018/11/6 0006
 */
public class Test_Singleton {

    public static class MyObject{
        private static MyObject myObject;

        //加上synchronize才能保证在多线程的情况下保证得到的对象单列，就如下面的列子
        public synchronized static MyObject getInstance(){
            if (myObject == null){
                myObject = new MyObject();
            }
            return myObject;
        }
    }

    public static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println(MyObject.getInstance().hashCode());
        }
    }

    public static void main(String[] args) {
        List<MyThread> list = new ArrayList();
        MyThread t;
        for (int i = 0; i < 50; i++) {
            t = new MyThread();
            list.add(t);
        }

        System.out.println(list.size());

        for (MyThread myThread : list) {
            myThread.start();
        }

        /*MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        MyThread t4 = new MyThread();
        MyThread t5 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();*/

    }

    /**
     * map的三种遍历方式
     */
    @Test
    public void mapIt(){

        Map map = new HashMap();
        map.put("1", "v1");
        map.put("2", "v2");
        map.put("3", "v3");
        map.put("4", "v4");
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "|" + value);
        }

        Set<Object> set1 = map.keySet();
        for (Object o : set1) {
            Object key = o;
            Object value = map.get(o);
            System.out.println(key + "|" + value);
        }

        Collection values = map.values();
        for (Object value : values) {
            System.out.println(value);
        }
    }

}
