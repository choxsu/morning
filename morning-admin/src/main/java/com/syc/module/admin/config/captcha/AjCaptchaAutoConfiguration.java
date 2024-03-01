package com.syc.module.admin.config.captcha;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xq.su
 * created on 2024/2/29
 */
@Configuration
@EnableConfigurationProperties({AjCaptchaProperties.class})
@Import({AjCaptchaServiceAutoConfiguration.class, AjCaptchaStorageAutoConfiguration.class})
public class AjCaptchaAutoConfiguration {
}
