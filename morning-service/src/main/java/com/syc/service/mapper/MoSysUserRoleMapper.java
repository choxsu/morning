package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSysUserRoleKey;

/**
 * @Entity com.syc.model.entity.MoSysUserRole
 */
public interface MoSysUserRoleMapper extends BaseMapper<MoSysUserRoleKey> {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(MoSysUserRoleKey key);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSysUserRoleKey record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSysUserRoleKey record);
}