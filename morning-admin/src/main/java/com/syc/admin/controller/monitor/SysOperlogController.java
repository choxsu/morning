package com.syc.admin.controller.monitor;


import com.syc.common.annotation.ApiResource;
import com.syc.common.annotation.Log;
import com.syc.common.domain.PageResult;
import com.syc.common.domain.R;
import com.syc.common.domain.entity.SysOperLogEntity;
import com.syc.common.enums.ResBizTypeEnum;
import com.syc.framework.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志记录
 */
@RestController
@RequestMapping("/monitor/operlog")
@Log(openLog = false)
@ApiResource(name = "操作日志管理", resBizType = ResBizTypeEnum.SYSTEM)
public class SysOperlogController {

    @Autowired
    private SysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
    @GetMapping(value = "/list", name = "操作日志-分页")
    public R list(SysOperLogEntity operLog) {
        PageResult<SysOperLogEntity> page = operLogService.selectOperLogPage(operLog);
        return R.ok().put(page);
    }


    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping(value = "/{operIds}", name = "操作日志-删除")
    public R remove(@PathVariable Long[] operIds) {
        return R.ok(operLogService.deleteOperLogByIds(operIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping(value = "/clean", name = "操作日志-清空")
    public R clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}
