package com.syc.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syc.model.entity.MoLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.syc.model.entity.MoLoginLog
 */
@Mapper
public interface MoLoginLogMapper extends BaseMapper {
    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insert(MoLoginLog record);

    /**
     *
     * @mbg.generated 2021-03-04 14:03:07
     */
    int insertSelective(MoLoginLog record);
}