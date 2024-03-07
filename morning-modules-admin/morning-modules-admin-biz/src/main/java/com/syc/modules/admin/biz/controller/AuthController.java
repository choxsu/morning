package com.syc.modules.admin.biz.controller;

import com.syc.modules.common.result.Result;
import com.syc.modules.admin.api.model.dto.CaptchaResult;
import com.syc.modules.admin.api.model.dto.LoginResult;
import com.syc.modules.admin.biz.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        LoginResult loginResult = authService.login(username, password);
        return Result.success(loginResult);
    }

    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    public Result logout() {
        authService.logout();
        return Result.success();
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result<CaptchaResult> getCaptcha() {
        CaptchaResult captcha = authService.getCaptcha();
        return Result.success(captcha);
    }

}
