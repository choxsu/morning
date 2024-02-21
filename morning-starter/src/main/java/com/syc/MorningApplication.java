package com.syc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 * scanBasePackages 这个是在分模块的情况下，扫描报下的自动配置,基于spring boot 2.0
 */
@SpringBootApplication
@RestController
public class MorningApplication {

    @RequestMapping("/")
    public String home() {
        return "index";
    }


    public static void main(String[] args) {
        //设置+8时区
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.of("+8")));
        SpringApplication.run(MorningApplication.class, args);
    }
}
