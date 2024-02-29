package com.syc.admin.controller.system;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.google.code.kaptcha.Producer;
import com.syc.common.annotation.Log;
import com.syc.common.constant.CacheConstants;
import com.syc.common.constant.Constants;
import com.syc.common.core.RedisCache;
import com.syc.common.domain.R;
import com.syc.common.utils.ServletUtils;
import com.syc.common.utils.ip.IpUtils;
import com.syc.common.utils.uuid.IdUtils;
import com.syc.framework.api.sysconfig.ConfigExpander;
import com.syc.framework.service.SysConfigService;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.xingyuv.captcha.service.CaptchaService;
/**
 * 验证码操作处理
 *
 * @author xq.su
 */
@RestController
@Log(openLog = false)
@Tag(name = "管理后台 - 验证码")
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysConfigService configService;

    @Resource
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @PostMapping(value = "/system/captcha/get", name = "生产验证码")
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
