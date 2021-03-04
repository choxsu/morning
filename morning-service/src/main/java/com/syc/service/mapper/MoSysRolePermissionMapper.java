package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSysRolePermissionKey;

/**
 * @Entity com.syc.model.entity.MoSysRolePermission
 */
public interface MoSysRolePermissionMapper extends BaseMapper<MoSysRolePermissionKey> {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(MoSysRolePermissionKey key);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSysRolePermissionKey record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSysRolePermissionKey record);
}