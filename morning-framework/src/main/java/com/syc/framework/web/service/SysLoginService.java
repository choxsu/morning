package com.syc.framework.web.service;


import cn.hutool.core.date.DateUtil;
import com.syc.common.constant.CacheConstants;
import com.syc.common.constant.Constants;
import com.syc.common.core.RedisCache;
import com.syc.common.domain.entity.SysUserEntity;
import com.syc.common.domain.model.LoginUser;
import com.syc.common.exception.ServiceException;
import com.syc.common.utils.MessageUtils;
import com.syc.common.utils.ServletUtils;
import com.syc.common.utils.StringUtils;
import com.syc.common.utils.ip.IpUtils;
import com.syc.common.utils.web.WebFrameworkUtils;
import com.syc.framework.manager.AsyncFactory;
import com.syc.framework.manager.AsyncManager;
import com.syc.framework.security.context.AuthenticationContextHolder;
import com.syc.framework.service.SysConfigService;
import com.syc.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author xq.su
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //异步执行->保存登录信息
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, null, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new ServiceException("账号或密码错误");
            } else {
                //异步执行->登录信息
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, null, Constants.LOGIN_FAIL, e.getMessage()));
                e.printStackTrace();
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //异步记录登录成功日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, loginUser.getUserId(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        // 额外设置到 request 中，有的过滤器在 Spring Security 之前
        WebFrameworkUtils.setLoginUserId(ServletUtils.getRequest(), loginUser.getUserId());
        //记录登录信息
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new ServiceException("验证码失效");
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new ServiceException("验证码错误");
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUserEntity sysUser = new SysUserEntity();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtil.date());
        userService.updateUserProfile(sysUser);
    }
}
