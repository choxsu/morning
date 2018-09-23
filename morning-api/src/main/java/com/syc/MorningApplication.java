package com.syc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 * scanBasePackages 这个是在分模块的情况下，扫描报下的自动配置
 */
@SpringBootApplication(scanBasePackages = {"com.syc"})
@EnableCaching
@MapperScan("com.syc.model.entity.mybatis.dao")
public class MorningApplication {
    public static void main(String[] args) {
        SpringApplication.run(MorningApplication.class, args);
    }
}
