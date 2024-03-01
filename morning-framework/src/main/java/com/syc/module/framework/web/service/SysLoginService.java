package com.syc.module.framework.web.service;


import cn.hutool.core.date.DateUtil;
import com.syc.module.common.constant.Constants;
import com.syc.module.common.core.RedisCache;
import com.syc.module.common.domain.entity.SysUserEntity;
import com.syc.module.common.domain.model.LoginBody;
import com.syc.module.common.domain.model.LoginUser;
import com.syc.module.common.enums.CommonStatusEnum;
import com.syc.module.common.enums.UserTypeEnum;
import com.syc.module.common.exception.ServiceException;
import com.syc.module.common.utils.MessageUtils;
import com.syc.module.common.utils.ServletUtils;
import com.syc.module.common.utils.ip.IpUtils;
import com.syc.module.common.utils.validation.ValidationUtils;
import com.syc.module.common.utils.web.WebFrameworkUtils;
import com.syc.module.framework.enums.logger.LoginLogTypeEnum;
import com.syc.module.framework.enums.logger.LoginResultEnum;
import com.syc.module.framework.logger.dto.LoginLogCreateReqDTO;
import com.syc.module.framework.manager.AsyncFactory;
import com.syc.module.framework.manager.AsyncManager;
import com.syc.module.framework.security.context.AuthenticationContextHolder;
import com.syc.module.framework.service.SysConfigService;
import com.syc.module.framework.service.SysUserService;
import com.syc.module.framework.web.service.vo.AuthLoginRespVO;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Objects;

/**
 * 登录校验方法
 *
 * @author xq.su
 */
@Component
public class SysLoginService {
    @Resource
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private Validator validator;
    @Resource
    private CaptchaService captchaService;

    @Resource
    private SysUserService userService;

    @Resource
    private SysConfigService configService;

    /**
     * 登录验证
     *
     * @param loginBody 登录参数
     * @return 结果
     */
    public AuthLoginRespVO login(LoginBody loginBody) {
        // 验证码验证
        validateCaptcha(loginBody);
        // 用户验证
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //异步执行->保存登录信息
                createLoginLog(null, loginBody.getUsername(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.password.not.match"));
                throw new ServiceException("账号或密码错误");
            } else {
                //异步执行->登录信息
                createLoginLog(null, loginBody.getUsername(), Constants.LOGIN_SUCCESS, e.getMessage());
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //异步记录登录成功日志
        createLoginLog(loginUser.getUserId(), loginBody.getUsername(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
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
     * @param reqVO 用户
     * @return 结果
     */
    public void validateCaptcha(LoginBody reqVO) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (!captchaEnabled) {
            return;
        }
        // 校验验证码
        ValidationUtils.validate(validator, reqVO, LoginBody.CodeEnableGroup.class);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(reqVO.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        // 验证不通过
        if (!response.isSuccess()) {
            createLoginLog(null, reqVO.getUsername(), Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"));
            throw new ServiceException(response.getRepMsg());
        }
    }

    private void createLoginLog(Long userId, String username,String loginResult, String msg) {
        // 插入登录日志
        AsyncManager.me().execute(
                AsyncFactory.recordLogininfor(username, userId, loginResult, msg));
        // 更新最后登录时间
        if (userId != null && Constants.LOGIN_SUCCESS.equals(loginResult)) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
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
