package com.syc.framework.api.mail;

import com.syc.framework.api.sysconfig.ConfigContext;

/**
 * 读取邮箱的配置
 *
 * @author xq.su
 */
public class MailConfigRead {

    public static String getSmtpHost() {
        return ConfigContext.me().selectConfigByKey("sys.email.smtp.host", String.class);
    }

    public static String getSmtpPort() {
        return ConfigContext.me().selectConfigByKey("sys.email.smtp.port", String.class);
    }

    public static String getSendAccount() {
        return ConfigContext.me().selectConfigByKey("sys.email.send.account", String.class);
    }

    public static String getPassword() {
        return ConfigContext.me().selectConfigByKey("sys.email.password", String.class);
    }

}
