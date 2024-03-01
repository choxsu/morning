package com.syc.module.framework.api.sysconfig;


import cn.hutool.extra.spring.SpringUtil;
import com.syc.module.framework.service.SysConfigService;

/**
 * 系统配置
 *
 * @author xq.su
 */
public class ConfigContext {

    /**
     * 获取系统配置操作接口
     */
    public static SysConfigService me() {
        return SpringUtil.getBean(SysConfigService.class);
    }

}
