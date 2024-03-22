package com.syc.modules.admin.biz.filter;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.util.StrUtil;
import com.syc.modules.common.constant.SecurityConstants;
import com.syc.modules.common.result.ResultCode;
import com.syc.modules.common.utils.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * 图形验证码校验过滤器
 *
 * @author haoxr
 * @since 2022/10/1
 */
public class CaptchaValidationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher LOGIN_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(com.syc.framework.security.constant.SecurityConstants.LOGIN_PATH, "POST");

    public static final String CAPTCHA_CODE_PARAM_NAME = "captchaCode";
    public static final String CAPTCHA_KEY_PARAM_NAME = "captchaKey";

    private final RedisTemplate<String, Object> redisTemplate;

    private final CodeGenerator codeGenerator;

    public CaptchaValidationFilter(RedisTemplate<String, Object> redisTemplate, CodeGenerator codeGenerator) {
        this.redisTemplate = redisTemplate;
        this.codeGenerator = codeGenerator;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 检验登录接口的验证码
        if (LOGIN_PATH_REQUEST_MATCHER.matches(request)) {
            // 请求中的验证码
            String captchaCode = request.getParameter(CAPTCHA_CODE_PARAM_NAME);
            // 缓存中的验证码
            String verifyCodeKey = request.getParameter(CAPTCHA_KEY_PARAM_NAME);
            String cacheVerifyCode = (String) redisTemplate.opsForValue().get(SecurityConstants.CAPTCHA_CODE_PREFIX + verifyCodeKey);
            if (cacheVerifyCode == null) {
                ResponseUtils.writeErrMsg(response, ResultCode.VERIFY_CODE_TIMEOUT);
            } else {
                // 验证码比对
                if (codeGenerator.verify(cacheVerifyCode, captchaCode)) {
                    chain.doFilter(request, response);
                } else {
                    ResponseUtils.writeErrMsg(response, ResultCode.VERIFY_CODE_ERROR);
                }
            }
        } else {
            // 非登录接口放行
            chain.doFilter(request, response);
        }
    }

}
