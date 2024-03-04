package com.syc.module.admin.controller.system;


import cn.hutool.core.util.StrUtil;
import com.syc.module.common.annotation.Log;
import com.syc.module.common.utils.ip.IpUtils;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.xingyuv.captcha.service.impl.BlockPuzzleCaptchaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * 验证码操作处理
 *
 * @author xq.su
 */
@RestController
@RequestMapping("/system/captcha")
@Tag(name = "管理后台 - 验证码")
public class CaptchaController {


    @Resource
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @PostMapping(value = "/get", name = "生产验证码")
    @Operation(summary = "生产验证码", description = "调用此接口获取验证码")
    public ResponseModel getCode(@RequestBody CaptchaVO data, HttpServletRequest request)  {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.get(data);
    }


    @PostMapping("/check")
    @Operation(summary = "校验验证码")
    public ResponseModel check(@RequestBody CaptchaVO data, HttpServletRequest request) {
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.check(data);
    }

    public static String getRemoteId(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        String ua = request.getHeader("user-agent");
        if (StrUtil.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }
}
