package com.syc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.syc.model.entity.MoSysUser;
import com.syc.service.mapper.MoSysUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.management.MXBean;
import java.util.Date;
import java.util.List;

/**
 * @author chox su
 * @date 2018/01/17 18:04
 * scanBasePackages 这个是在分模块的情况下，扫描报下的自动配置,基于spring boot 2.0
 */
@SpringBootApplication(scanBasePackages = {"com.syc"})
@Controller
public class MorningApplication {

    @RequestMapping("/")
    public String home() {
        return "index";
    }


    public static void main(String[] args) {
        SpringApplication.run(MorningApplication.class, args);
    }


    @Resource
    private MoSysUserMapper moSysUserMapper;
}
