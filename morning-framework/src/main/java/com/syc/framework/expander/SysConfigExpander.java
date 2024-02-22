package com.syc.framework.expander;


import com.syc.framework.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 系统配置读取
 */
@Component
@RequiredArgsConstructor
public class SysConfigExpander {

    private static SysConfigService configService;

    final SysConfigService sysConfigService;

    @PostConstruct
    public void init() {
        configService = sysConfigService;
    }


    /**
     * 用户默认头像url
     */
    public static String getUserDefaultAvatar() {
        return configService.selectConfigByKey("sys.user.default.avatar");
    }

    /**
     * 验证码类型
     *
     * @return
     */
    public static String getLoginCaptchaType() {
        return configService.selectConfigByKey("sys.login.captchaType", String.class, "math");
    }


    /**
     * 获取文件保存目录
     *
     * @return
     */
    public static String getFileProfile() {

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return configService.selectConfigByKey("sys.local.profile.win", String.class, "D:\\uploadPath");
        }
        if (osName.contains("mac")) {
            return configService.selectConfigByKey("sys.local.profile.mac", String.class, "~/uploadPath");
        }
        if (osName.contains("linux")) {
            return configService.selectConfigByKey("sys.local.profile.linux", String.class, "/data/uploadPath");
        }
        return null;
    }

}
