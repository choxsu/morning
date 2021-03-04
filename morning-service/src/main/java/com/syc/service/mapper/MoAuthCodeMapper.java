package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoAuthCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoAuthCode
 */
@Mapper
public interface MoAuthCodeMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoAuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoAuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    MoAuthCode selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKeySelective(MoAuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKey(MoAuthCode record);
}