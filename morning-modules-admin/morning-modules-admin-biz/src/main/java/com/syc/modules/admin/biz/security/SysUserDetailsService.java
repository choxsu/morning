package com.syc.modules.admin.biz.security;

import com.syc.framework.security.model.SysUserDetails;
import com.syc.framework.security.model.UserAuthInfo;
import com.syc.modules.admin.biz.services.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 系统用户认证
 *
 * @author haoxr
 */
@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuthInfo userAuthInfo = sysUserService.getUserAuthInfo(username);
        if (userAuthInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SysUserDetails(userAuthInfo);
    }
}
