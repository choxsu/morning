package com.syc.module.admin.controller.monitor;


import com.syc.module.common.annotation.ApiResource;
import com.syc.module.common.annotation.Log;
import com.syc.module.common.domain.PageResult;
import com.syc.module.common.domain.R;
import com.syc.module.common.domain.entity.SysLoginLogEntity;
import com.syc.module.common.enums.ResBizTypeEnum;
import com.syc.module.framework.service.SysLoginLogService;
import com.syc.module.framework.web.service.SysPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 系统访问记录
 *
 * @author xq.su
 */
@RestController
@RequestMapping("/monitor/logininfor")
@Log(openLog = false)
@ApiResource(name = "登录日志管理", resBizType = ResBizTypeEnum.SYSTEM)
public class SysLogininforController {
    @Autowired
    private SysLoginLogService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping(value = "/list", name = "登录日志-分类列表")
    public R list(SysLoginLogEntity logininfor) {
        PageResult<SysLoginLogEntity> page = logininforService.selectLogininforPage(logininfor);
        return R.ok().put(page);
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @DeleteMapping(value = "/{infoIds}", name = "登录日志-删除")
    public R remove(@PathVariable Long[] infoIds) {
        return R.ok(logininforService.deleteLogininforByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @DeleteMapping(value = "/clean", name = "登录日志-清空")
    public R clean() {
        logininforService.cleanLogininfor();
        return R.ok();
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:unlock')")
    @GetMapping(value = "/unlock/{userName}", name = "登录日志-解锁")
    public R unlock(@PathVariable("userName") String userName) {
        passwordService.clearLoginRecordCache(userName);
        return R.ok();
    }
}
