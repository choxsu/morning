package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.service.LoginService;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 * Updated by xiaoqiu.su 2024-04-12
 */
@RestController
public class IndexController {

    @Resource
    private XxlJobService xxlJobService;
    @Resource
    private LoginService loginService;


    @GetMapping("/dashboard")
    public ReturnT<Map<String, Object>> index() {
        Map<String, Object> dashboardMap = xxlJobService.dashboardInfo();
        return new ReturnT<>(dashboardMap);
    }

    @GetMapping("/chartInfo")
    public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {
        return xxlJobService.chartInfo(startDate, endDate);
    }

    @PostMapping("login")
    @PermissionLimit(limit = false)
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember) {
        boolean ifRem = ifRemember != null && ifRemember.trim().length() > 0 && "on".equals(ifRemember);
        return loginService.login(request, response, userName, password, ifRem);
    }

    @PostMapping("logout")
    @PermissionLimit(limit = false)
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return loginService.logout(request, response);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
