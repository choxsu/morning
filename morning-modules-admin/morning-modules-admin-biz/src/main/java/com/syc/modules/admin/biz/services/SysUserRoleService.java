package com.syc.modules.admin.biz.services;


import com.baomidou.mybatisplus.extension.service.IService;
import com.syc.modules.admin.api.model.entity.SysUserRole;


import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRoles(Long userId, List<Long> roleIds);

    /**
     * 判断角色是否存在绑定的用户
     *
     * @param roleId 角色ID
     * @return true：已分配 false：未分配
     */
    boolean hasAssignedUsers(Long roleId);
}
