package com.syc.modules.admin.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.syc.modules.admin.api.model.bo.RouteBO;
import com.syc.modules.admin.api.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 菜单持久接口层
 *
 * @author xq.su
 */

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<RouteBO> listRoutes();

    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(Set<String> roles);
}
