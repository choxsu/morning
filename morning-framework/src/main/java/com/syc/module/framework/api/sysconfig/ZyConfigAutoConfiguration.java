package com.syc.module.framework.api.sysconfig;


import com.syc.module.framework.service.SysConfigService;
import com.syc.module.framework.service.impl.SysConfigServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统配置自动配置
 */
@Configuration
public class ZyConfigAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SysConfigService.class)
    public SysConfigService configService() {
        return new SysConfigServiceImpl();
    }
}
