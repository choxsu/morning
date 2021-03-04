package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoCategory
 */
@Mapper
public interface MoCategoryMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    MoCategory selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKeySelective(MoCategory record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKey(MoCategory record);
}