package com.syc.server;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 */
@SpringBootApplication(scanBasePackages = {"com.syc.server", "com.syc.modules", "com.syc.framework"})
@MapperScan("com.syc.modules.**.**.mapper")
public class MorningApplication {


    public static void main(String[] args) {
        //设置+8时区
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.of("+8")));
        SpringApplication.run(MorningApplication.class, args);
    }
}
