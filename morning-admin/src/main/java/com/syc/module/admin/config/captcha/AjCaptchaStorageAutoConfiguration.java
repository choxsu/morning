package com.syc.module.admin.config.captcha;

import com.xingyuv.captcha.service.CaptchaCacheService;
import com.xingyuv.captcha.service.impl.CaptchaServiceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xq.su
 * created on 2024/2/29
 */
@Configuration
public class AjCaptchaStorageAutoConfiguration {
    public AjCaptchaStorageAutoConfiguration() {
    }

    @Bean(
            name = {"AjCaptchaCacheService"}
    )
    @ConditionalOnMissingBean
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        return CaptchaServiceFactory.getCache(config.getCacheType().name());
    }
}

