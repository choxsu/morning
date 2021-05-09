package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoSession;

/**
 * @Entity com.syc.model.entity.MoSession
 */
public interface MoSessionMapper extends BaseMapper<MoSession> {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insert(MoSession record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int insertSelective(MoSession record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    MoSession selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKeySelective(MoSession record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:59
     */
    int updateByPrimaryKey(MoSession record);
}