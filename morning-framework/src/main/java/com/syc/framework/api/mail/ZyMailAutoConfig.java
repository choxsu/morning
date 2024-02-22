package com.syc.framework.api.mail;

import com.syc.framework.api.mail.impl.MailServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xq.su
 */
@Configuration
public class ZyMailAutoConfig {


    /**
     * mail发送邮件接口
     */
    @Bean
    @ConditionalOnMissingBean(MailSendApi.class)
    public MailSendApi mailSenderApi() {
        return new MailServiceImpl();
    }
}
