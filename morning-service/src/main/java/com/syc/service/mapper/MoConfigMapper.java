package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoConfig
 */
@Mapper
public interface MoConfigMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int deleteByPrimaryKey(String key);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoConfig record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoConfig record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    MoConfig selectByPrimaryKey(String key);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKeySelective(MoConfig record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKey(MoConfig record);
}