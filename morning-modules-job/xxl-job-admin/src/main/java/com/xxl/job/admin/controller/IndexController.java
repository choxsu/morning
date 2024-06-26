package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.pojo.LoginParam;
import com.xxl.job.admin.service.LoginService;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    public ReturnT<Map<String, String>> loginDo(HttpServletResponse response, @RequestBody LoginParam loginParam) {
        return loginService.login(response, loginParam);
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
