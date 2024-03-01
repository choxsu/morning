package com.syc.module.admin.controller.system;



import com.syc.module.common.domain.R;
import com.syc.module.common.config.SysConfig;
import com.syc.module.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 首页
 *
 * @author xq.su
 */
@RestController
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private SysConfig sysConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", sysConfig.getName(), sysConfig.getVersion());
    }


    /**
     * 版本情况
     */
    @RequestMapping("/version")
    public R version() {
        HashMap<String, String> map = new HashMap<>(8);
        map.put("version", sysConfig.getVersion());
        map.put("frameworkVersion", "0.0.1");
        return R.ok(map);
    }
}
