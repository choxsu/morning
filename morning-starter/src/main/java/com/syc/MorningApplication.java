package com.syc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 */
@SpringBootApplication
public class MorningApplication {


    public static void main(String[] args) {
        //设置+8时区
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.of("+8")));
        SpringApplication.run(MorningApplication.class, args);
    }
}
