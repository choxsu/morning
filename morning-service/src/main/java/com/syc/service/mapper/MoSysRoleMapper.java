package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSysRole;

/**
 * @Entity com.syc.model.entity.MoSysRole
 */
public interface MoSysRoleMapper extends BaseMapper<MoSysRole> {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSysRole record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSysRole record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    MoSysRole selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKeySelective(MoSysRole record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKey(MoSysRole record);
}