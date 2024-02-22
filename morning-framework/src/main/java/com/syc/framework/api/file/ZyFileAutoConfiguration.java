package com.syc.framework.api.file;


import com.syc.framework.api.sysconfig.ConfigExpander;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件的自动配置
 * @author xq.su
 */
@Configuration
public class ZyFileAutoConfiguration {

    /**
     * 本地文件操作
     */
    @Bean
    @ConditionalOnMissingBean(FileOperatorApi.class)
    public FileOperatorApi fileOperatorApi() {
        return new LocalFileOperator(ConfigExpander.getFileProfile());
    }

}
