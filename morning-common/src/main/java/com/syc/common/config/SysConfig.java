package com.syc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 首页
 *
 * @author xq.su
 */

@Data
@Component
@ConfigurationProperties(prefix = "sys")
public class SysConfig {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

}
