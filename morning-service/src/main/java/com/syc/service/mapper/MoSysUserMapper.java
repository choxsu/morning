package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSysUser;

/**
 * @Entity com.syc.model.entity.MoSysUser
 */
public interface MoSysUserMapper extends BaseMapper<MoSysUser> {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSysUser record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSysUser record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    MoSysUser selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKeySelective(MoSysUser record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKey(MoSysUser record);
}