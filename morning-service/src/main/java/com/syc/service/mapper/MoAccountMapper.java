package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoAccount
 */
@Mapper
public interface MoAccountMapper extends BaseMapper<MoAccount> {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoAccount record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoAccount record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    MoAccount selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKeySelective(MoAccount record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int updateByPrimaryKey(MoAccount record);
}