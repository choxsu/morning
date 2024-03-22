package com.syc.modules.admin.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.modules.admin.api.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色访问层
 *
 * @author xq.su
 * @since 2022/1/15
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取角色绑定的用户数
     *
     * @param roleId 角色ID
     */
    int countUsersForRole(Long roleId);
}
