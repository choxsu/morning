package com.syc.modules.admin.biz.config;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import com.syc.modules.admin.biz.plugin.captcha.CaptchaProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * 验证码自动装配配置类，负责配置和生成验证码相关的组件
 *
 * @author xq.su
 * @since 2023/11/24
 */
@Configuration
public class CaptchaConfig {

    // 注入验证码配置属性
    @Resource
    private CaptchaProperties captchaProperties;

    /**
     * 根据配置创建验证码生成器。
     * 支持的类型有"math"（数学算式）和"random"（随机字符）。
     *
     * @return CodeGenerator 验证码生成器实例
     */
    @Bean
    public CodeGenerator codeGenerator() {
        String codeType = captchaProperties.getCode().getType(); // 验证码类型
        int codeLength = captchaProperties.getCode().getLength(); // 验证码长度
        if ("math".equalsIgnoreCase(codeType)) {
            return new MathGenerator(codeLength); // 数学算式验证码
        } else if ("random".equalsIgnoreCase(codeType)) {
            return new RandomGenerator(codeLength); // 随机字符验证码
        } else {
            throw new IllegalArgumentException("Invalid captcha generator type: " + codeType);
        }
    }

    /**
     * 配置验证码所使用的字体。
     *
     * @return Font 验证码字体实例
     */
    @Bean
    public Font captchaFont() {
        String fontName = captchaProperties.getFont().getName(); // 字体名称
        int fontSize = captchaProperties.getFont().getSize(); // 字体大小
        int fontWight = captchaProperties.getFont().getWeight(); // 字体粗细
        return new Font(fontName, fontWight, fontSize); // 返回配置的字体
    }

}
