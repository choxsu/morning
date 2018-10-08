package com.syc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.MXBean;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 * scanBasePackages 这个是在分模块的情况下，扫描报下的自动配置
 */
@SpringBootApplication(scanBasePackages = {"com.syc"})
@EnableCaching
@MapperScan("com.syc.model.entity.mybatis.dao")
@RestController
public class MorningApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }


    public static void main(String[] args) {
        /*System.out.println("hello world");
        Thread thread = Thread.currentThread();
        ThreadGroup group = thread.getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        int nowThreads = topGroup.activeCount();
        Thread[] lstThreads = new Thread[nowThreads];
        topGroup.enumerate(lstThreads);
        for (int i = 0; i < nowThreads; i++) {
            System.out.println("线程number：" + (i+1) + " = " + lstThreads[i].getName());
        }*/
        SpringApplication.run(MorningApplication.class, args);
    }
}
