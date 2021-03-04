package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSysPermission;

/**
 * @Entity com.syc.model.entity.MoSysPermission
 */
public interface MoSysPermissionMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSysPermission record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSysPermission record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    MoSysPermission selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKeySelective(MoSysPermission record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKey(MoSysPermission record);
}