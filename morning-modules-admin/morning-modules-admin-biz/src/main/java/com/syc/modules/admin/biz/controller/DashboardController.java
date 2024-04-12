package com.syc.modules.admin.biz.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.syc.modules.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoqiu.su
 * created on 2024/4/1
 */
@Tag(name = "00.Dashboard页面")
@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/get/weather")
    @Operation(summary = "获取天气信息")
    public Result<JSONObject> getWeather() {
        String data = HttpUtil.get("https://weather.cma.cn/api/weather/view");
        if (JSONUtil.isTypeJSONObject(data)) {
            return Result.success(JSONUtil.parseObj(data).getJSONObject("data"));
        }
        return Result.failed("查询失败");
    }

}
