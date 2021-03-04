package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoOrders;

/**
 * @Entity com.syc.model.entity.MoOrders
 */
public interface MoOrdersMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    int insert(MoOrders record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    int insertSelective(MoOrders record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    MoOrders selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    int updateByPrimaryKeySelective(MoOrders record);

    /**
     *
     * @mbg.generated 2021-03-04 14:11:35
     */
    int updateByPrimaryKey(MoOrders record);
}