package com.syc.framework.security.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.syc.framework.security.model.SysUserDetails;
import com.syc.modules.common.constant.SystemConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xq.su
 */
public class SecurityUtils {

    /**
     * 获取当前登录人信息
     *
     * @return SysUserDetails
     */
    public static SysUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SysUserDetails) {
                return (SysUserDetails) authentication.getPrincipal();
            }
        }
        return null;
    }

    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static Long getUserId() {
        return Convert.toLong(Objects.requireNonNull(getUser()).getUserId());
    }

    /**
     * 获取部门ID
     *
     * @return
     */
    public static Long getDeptId() {
        return Convert.toLong(Objects.requireNonNull(getUser()).getDeptId());
    }

    /**
     * 获取数据权限范围
     *
     * @return DataScope
     */
    public static Integer getDataScope() {
        return Convert.toInt(Objects.requireNonNull(getUser()).getDataScope());
    }


    /**
     * 获取用户角色集合
     *
     * @return 角色集合
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (CollectionUtil.isNotEmpty(authorities)) {
                return authorities.stream().filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item -> StrUtil.removePrefix(item.getAuthority(), "ROLE_"))
                        .collect(Collectors.toSet());
            }
        }
        return new HashSet<>();
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     *
     * @return
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles.contains(SystemConstants.ROOT_ROLE_CODE);
    }

}
