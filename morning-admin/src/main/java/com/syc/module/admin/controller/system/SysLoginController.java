package com.syc.module.admin.controller.system;


import com.syc.module.common.annotation.ApiResource;
import com.syc.module.common.constant.Constants;
import com.syc.module.common.domain.R;
import com.syc.module.common.domain.entity.SysMenuEntity;
import com.syc.module.common.domain.entity.SysUserEntity;
import com.syc.module.common.domain.model.LoginBody;
import com.syc.module.common.enums.ResBizTypeEnum;
import com.syc.module.common.utils.SecurityUtils;
import com.syc.module.framework.service.SysMenuService;
import com.syc.module.framework.web.service.SysLoginService;
import com.syc.module.framework.web.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/system/auth")
@ApiResource(name = "登录路由", resBizType = ResBizTypeEnum.SYSTEM)
@Tag(name = "登录路由", description = "用于登录相关的操作")
public class SysLoginController {

    @Autowired
    private SysMenuService menuService;
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping(value = "/login", name = "登录方法")
    @Operation(tags = "登录路由", summary = "登录方法")
    public R login(@RequestBody @Valid LoginBody loginBody) {
        return R.ok(loginService.login(loginBody));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping(value = "getInfo", name = "获取用户信息")
    @Operation(tags = "登录路由", summary = "获取用户信息")
    public R getInfo() {
        SysUserEntity user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        R ajax = R.ok();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping(value = "getRouters", name = "获取路由信息")
    @Operation(tags = "登录路由", summary = "获取路由信息")
    public R getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenuEntity> menus = menuService.selectMenuTreeByUserId(userId);
        return R.ok(menuService.buildMenus(menus));
    }

}
