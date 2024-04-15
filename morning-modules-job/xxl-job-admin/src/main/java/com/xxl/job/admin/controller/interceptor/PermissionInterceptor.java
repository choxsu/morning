package com.xxl.job.admin.controller.interceptor;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.exception.XxlJobException;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 *
 * @author xuxueli 2015-12-12 18:09:04
 */
@Component
public class PermissionInterceptor implements AsyncHandlerInterceptor {

    @Resource
    private LoginService loginService;

    /**
     * 此方法为拦截器的前置处理方法，用于在请求处理之前进行拦截。
     * 主要用于权限检查，判断是否需要登录以及是否需要特定权限。
     *
     * @param request  HttpServletRequest对象，代表客户端的HTTP请求
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param handler  将要处理请求的处理器对象
     * @return boolean 返回false表示拦截请求，不再继续向下传递；返回true表示放行，继续向下传递。
     * @throws Exception 抛出异常，用于处理权限不足等异常情况。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断处理器是否为HandlerMethod类型，不是则直接放行
        if (!(handler instanceof HandlerMethod method)) {
            return true;    // proceed with the next interceptor
        }

        // 初始化权限检查标志
        boolean needLogin = true;
        boolean needAdminuser = false;
        // 检查方法上是否标注了PermissionLimit注解，并读取注解配置
        PermissionLimit permission = method.getMethodAnnotation(PermissionLimit.class);
        if (permission != null) {
            needLogin = permission.limit();
            needAdminuser = permission.adminuser();
        } else if (method.getBean().getClass().isAnnotationPresent(PermissionLimit.class)) {
            // 如果类上标注了PermissionLimit注解，进行相应的处理
            PermissionLimit classPermission = method.getBean().getClass().getAnnotation(PermissionLimit.class);
            if (classPermission != null) {
                needLogin = classPermission.limit();
                needAdminuser = classPermission.adminuser();
            }
        }

        // 需要登录的处理
        if (needLogin) {
            // 检查用户是否登录
            XxlJobUser loginUser = loginService.ifLogin(request, response);
            if (loginUser == null) {
                throw new XxlJobException(I18nUtil.getString("system_no_login"));
            }
            // 检查是否需要管理员权限，并进行验证
            if (needAdminuser && loginUser.getRole() != 1) {
                throw new XxlJobException(I18nUtil.getString("system_permission_limit"));
            }
            // 设置登录用户信息，供后续使用
            request.setAttribute(LoginService.LOGIN_IDENTITY_KEY, loginUser);
        }

        return true;    // proceed with the next interceptor
    }


}
