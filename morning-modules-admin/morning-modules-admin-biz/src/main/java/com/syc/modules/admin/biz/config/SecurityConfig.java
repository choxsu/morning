package com.syc.modules.admin.biz.config;

import cn.hutool.captcha.generator.CodeGenerator;
import com.syc.framework.security.constant.SecurityConstants;
import com.syc.framework.security.exception.MyAccessDeniedHandler;
import com.syc.framework.security.exception.MyAuthenticationEntryPoint;
import com.syc.modules.admin.biz.filter.CaptchaValidationFilter;
import com.syc.modules.admin.biz.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 权限配置类，用于配置应用的访问安全策略。
 *
 * @author xq.su
 * @since 2023/2/17
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * 异常处理和登录入口等服务的注入
     */
    private final MyAuthenticationEntryPoint authenticationEntryPoint;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CodeGenerator codeGenerator;

    /**
     * 配置安全过滤链，定义请求的访问权限。
     *
     * @param http 用于构建和配置HttpSecurity的接口
     * @return 返回配置好的SecurityFilterChain
     * @throws Exception 可能抛出的异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requestMatcherRegistry ->
                        requestMatcherRegistry.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .csrf(AbstractHttpConfigurer::disable)

        ;

        // 添加验证码和JWT验证过滤器到过滤链中
        http.addFilterBefore(new CaptchaValidationFilter(redisTemplate,codeGenerator), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtValidationFilter(redisTemplate), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 配置忽略的URL，这些URL不会被Spring Security过滤。
     *
     * @return 返回WebSecurityCustomizer实例
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/api/v1/auth/captcha",
                        "/webjars/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/ws/**",
                        "/ws-app/**"
                );
    }

    /**
     * 配置密码编码器，使用BCrypt算法。
     *
     * @return 返回BCryptPasswordEncoder实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 手动注入AuthenticationManager。
     *
     * @param authenticationConfiguration 认证配置
     * @return 返回配置好的AuthenticationManager
     * @throws Exception 可能抛出的异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
