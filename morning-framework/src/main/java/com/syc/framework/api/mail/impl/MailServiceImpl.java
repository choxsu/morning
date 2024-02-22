package com.syc.framework.api.mail.impl;


import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.syc.framework.api.mail.MailConfigRead;
import com.syc.framework.api.mail.MailSendApi;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xq.su
 */
@Service
public class MailServiceImpl implements MailSendApi {

    /**
     * 异步发送邮件
     *
     * @param tos     对方的邮箱地址，可以是单个，也可以是多个（Collection表示）
     * @param subject 标题
     * @param content 邮件正文，可以是文本，也可以是HTML内容
     * @param isHtml  是否为HTML，如果是，那参数content识别为HTML内容
     */
    @Async
    @Override
    public void sendMail(List<String> tos, String subject, String content, Boolean isHtml) {

        String host = MailConfigRead.getSmtpHost();
        String port = MailConfigRead.getSmtpPort();
        String account = MailConfigRead.getSendAccount();
        String password = MailConfigRead.getPassword();

        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(host);
        mailAccount.setPort(Integer.parseInt(port));
        mailAccount.setAuth(true);
        mailAccount.setFrom(account);
        mailAccount.setPass(password);
        //在使用QQ或Gmail邮箱时，需要强制开启SSL支持
        if ("smtp.qq.com".equalsIgnoreCase(host) || "smtp.gmail.com".equalsIgnoreCase(host)) {
            mailAccount.setSslEnable(true);
        }

        MailUtil.send(mailAccount, tos, subject, content, isHtml);

    }


}
