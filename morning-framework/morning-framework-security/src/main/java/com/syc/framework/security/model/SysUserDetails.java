package com.syc.framework.security.model;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security 用户对象
 *
 * @author xq.su
 */
@Data
@NoArgsConstructor
public class SysUserDetails implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Boolean enabled;

    private Collection<SimpleGrantedAuthority> authorities;

    private Set<String> perms;

    private Long deptId;

    private Integer dataScope;

    public SysUserDetails(UserAuthInfo user) {
        this.userId = user.getUserId();
        Set<String> roles = user.getRoles();
        Set<SimpleGrantedAuthority> authorities;
        if (CollectionUtil.isNotEmpty(roles)) {
            authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toSet());
        } else {
            authorities = new HashSet<>();
        }
        this.authorities = authorities;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = ObjectUtil.equal(user.getStatus(), 1);
        this.perms = user.getPerms();
        this.deptId = user.getDeptId();
        this.dataScope = user.getDataScope();
    }

    public Long getUserId() {
        return this.userId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
