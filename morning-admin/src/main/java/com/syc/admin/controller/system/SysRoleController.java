package com.syc.admin.controller.system;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syc.common.annotation.ApiResource;
import com.syc.common.domain.PageResult;
import com.syc.common.domain.R;
import com.syc.common.domain.entity.SysRoleEntity;
import com.syc.common.domain.entity.SysUserEntity;
import com.syc.common.domain.entity.SysUserRoleEntity;
import com.syc.common.domain.model.LoginUser;
import com.syc.common.enums.ResBizTypeEnum;
import com.syc.common.utils.SecurityUtils;
import com.syc.common.utils.StringUtils;
import com.syc.framework.service.SysRoleService;
import com.syc.framework.service.SysUserService;
import com.syc.framework.web.service.SysPermissionService;
import com.syc.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/system/role")
@ApiResource(name = "角色管理", resBizType = ResBizTypeEnum.SYSTEM)
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public R list(SysRoleEntity role) {
        PageResult<SysRoleEntity> list = roleService.page(role);
        return R.ok().put(list);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public R getInfo(@PathVariable Long roleId) {
        return R.ok(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @PostMapping
    public R add(@Validated @RequestBody SysRoleEntity role) {
        if (!roleService.checkRoleNameUnique(role)) {
            return R.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(role)) {
            return R.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return R.ok(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping
    public R edit(@Validated @RequestBody SysRoleEntity role) {
        roleService.checkRoleAllowed(role);
        if (!roleService.checkRoleNameUnique(role)) {
            return R.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (!roleService.checkRoleKeyUnique(role)) {
            return R.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        if (roleService.updateRole(role) > 0) {
            // 更新缓存用户权限
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            permissionService.resetLoginUserRoleCache(role.getRoleId());
            return R.ok();
        }
        return R.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/changeStatus")
    public R changeStatus(@RequestBody SysRoleEntity role) {
        roleService.checkRoleAllowed(role);
        roleService.updateRoleStatus(role);
        //更新redis缓存权限数据
        permissionService.resetLoginUserRoleCache(role.getRoleId());
        return R.ok();
    }

    /**
     * 删除角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @DeleteMapping("/{roleIds}")
    public R remove(@PathVariable Long[] roleIds) {
        roleService.deleteRoleByIds(roleIds);
        //更新redis缓存权限数据
        Arrays.stream(roleIds).forEach(id -> permissionService.resetLoginUserRoleCache(id));

        return R.ok();
    }


    /**
     * 查询已分配用户角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/authUser/allocatedList")
    public R allocatedList(SysUserEntity user) {
        Page<SysUserEntity> page = userService.selectAllocatedList(user);
        return R.ok().put(page);
    }

    /**
     * 查询未分配用户角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/authUser/unallocatedList")
    public R unallocatedList(SysUserEntity user) {
        Page<SysUserEntity> page = userService.selectUnallocatedList(user);
        return R.ok().put(page);
    }

    /**
     * 取消授权用户
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/authUser/cancel")
    public R cancelAuthUser(@RequestBody SysUserRoleEntity userRole) {
        int i = roleService.deleteAuthUser(userRole);
        //更新redis缓存权限数据
        permissionService.resetLoginUserRoleCache(userRole.getRoleId());
        return R.ok(i);
    }

    /**
     * 批量取消授权用户
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/authUser/cancelAll")
    public R cancelAuthUserAll(Long roleId, Long[] userIds) {
        int i = roleService.deleteAuthUsers(roleId, userIds);
        //更新redis缓存权限数据
        permissionService.resetLoginUserRoleCache(roleId);

        return R.ok(i);
    }

    /**
     * 批量选择用户授权
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/authUser/selectAll")
    public R selectAuthUserAll(Long roleId, Long[] userIds) {
        return R.ok(roleService.insertAuthUsers(roleId, userIds));
    }

}
