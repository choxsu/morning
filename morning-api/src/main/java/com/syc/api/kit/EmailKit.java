

package com.syc.api.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 邮件发送工具类
 */
@Component
public class EmailKit {

    private static final Logger log = LoggerFactory.getLogger(EmailKit.class);

    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送纯文字 邮件
     *
     * @param toEmail
     * @param title
     * @param content
     * @return
     */
    public boolean sendEmail(String toEmail,
                             String title,
                             String content) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(username);
            email.setTo(toEmail);
            email.setSubject(title);
            email.setText(content);
            email.setSentDate(new Date());
            jms.send(email);
        } catch (MailException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 发送html 邮件
     *
     * @param toEmail
     * @param title
     * @param content
     * @return
     */
    public boolean sendHtmlEmail(String toEmail,
                                 String title,
                                 String content) {
        try {
            MimeMessage message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(toEmail);
            helper.setSubject(title);
            helper.setText(content, true);
            helper.setSentDate(new Date());
            jms.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}

		
	
	


