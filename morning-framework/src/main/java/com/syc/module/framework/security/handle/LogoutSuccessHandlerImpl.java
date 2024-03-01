package com.syc.module.framework.security.handle;


import com.alibaba.fastjson2.JSON;
import com.syc.module.common.constant.Constants;
import com.syc.module.common.constant.HttpStatus;
import com.syc.module.common.domain.R;
import com.syc.module.common.domain.model.LoginUser;
import com.syc.module.common.utils.ServletUtils;
import com.syc.module.common.utils.StringUtils;
import com.syc.module.framework.manager.AsyncFactory;
import com.syc.module.framework.manager.AsyncManager;
import com.syc.module.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author xq.su
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginUser.getUsername(), loginUser.getUserId(), Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(R.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
