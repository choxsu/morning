package com.syc.modules.admin.biz.services.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.syc.framework.security.util.JwtUtils;
import com.syc.modules.common.constant.SecurityConstants;
import com.syc.modules.common.enums.CaptchaTypeEnum;
import com.syc.modules.admin.api.model.dto.CaptchaResult;
import com.syc.modules.admin.api.model.dto.LoginResult;
import com.syc.modules.admin.biz.plugin.captcha.CaptchaProperties;
import com.syc.modules.admin.biz.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务实现类
 *
 * @author haoxr
 * @since 2.4.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String,Object> redisTemplate;
    private final CodeGenerator codeGenerator;
    private final Font captchaFont;
    private final CaptchaProperties captchaProperties;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public LoginResult login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username.toLowerCase().trim(), password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String accessToken = JwtUtils.generateToken(authentication);
        return LoginResult.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    /**
     * 注销
     */
    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotBlank(token)) {

            Map<String, Object> claims = JwtUtils.parseToken(token);
            String jti = Convert.toStr(claims.get(JWTPayload.JWT_ID));
            Long expiration = Convert.toLong(claims.get(JWTPayload.EXPIRES_AT));
            if (expiration != null) {
                long ttl = expiration - System.currentTimeMillis() / 1000;
                redisTemplate.opsForValue().set(SecurityConstants.BLACKLIST_TOKEN_PREFIX + jti, null, ttl, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(SecurityConstants.BLACKLIST_TOKEN_PREFIX + jti, null);
            }
        }
        SecurityContextHolder.clearContext();
    }

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    @Override
    public CaptchaResult getCaptcha() {

        String captchaType = captchaProperties.getType();
        int width = captchaProperties.getWidth();
        int height = captchaProperties.getHeight();
        int interfereCount = captchaProperties.getInterfereCount();
        int codeLength = captchaProperties.getCode().getLength();

        AbstractCaptcha captcha;
        if (CaptchaTypeEnum.CIRCLE.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createCircleCaptcha(width, height, codeLength, interfereCount);
        } else if (CaptchaTypeEnum.GIF.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createGifCaptcha(width, height, codeLength);
        } else if (CaptchaTypeEnum.LINE.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createLineCaptcha(width, height, codeLength, interfereCount);
        } else if (CaptchaTypeEnum.SHEAR.name().equalsIgnoreCase(captchaType)) {
            captcha = CaptchaUtil.createShearCaptcha(width, height, codeLength, interfereCount);
        } else {
            throw new IllegalArgumentException("Invalid captcha type: " + captchaType);
        }
        captcha.setGenerator(codeGenerator);
        captcha.setTextAlpha(captchaProperties.getTextAlpha());
        captcha.setFont(captchaFont);

        String captchaCode = captcha.getCode();
        String imageBase64Data = captcha.getImageBase64Data();

        // 验证码文本缓存至Redis，用于登录校验
        String captchaKey = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().set(SecurityConstants.CAPTCHA_CODE_PREFIX + captchaKey, captchaCode,
                captchaProperties.getExpireSeconds(), TimeUnit.SECONDS);

        return CaptchaResult.builder()
                .captchaKey(captchaKey)
                .captchaBase64(imageBase64Data)
                .build();
    }

}
