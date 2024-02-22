package com.syc.admin.controller.system;


import com.syc.common.annotation.Anonymous;
import com.syc.common.domain.R;
import com.syc.common.domain.entity.SysUserEntity;
import com.syc.common.domain.model.RegisterBody;
import com.syc.common.utils.StringUtils;
import com.syc.framework.service.SysConfigService;
import com.syc.framework.service.SysUserService;
import com.syc.framework.web.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册验证
 */
@RestController
public class SysRegisterController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private SysConfigService configService;

    @Autowired
    private SysUserService userService;

    @PostMapping("/register")
    public R register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return R.error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? R.ok() : R.error(msg);
    }

    @Anonymous
    @GetMapping("/userNameUnique")
    public R userNameUnique(String userName) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setUserName(userName);
        return R.ok(userService.checkUserNameUnique(userEntity));
    }

    @Anonymous
    @GetMapping("/emailUnique")
    public R emailUnique(String email) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setEmail(email);
        return R.ok(userService.checkEmailUnique(userEntity));
    }

}
