package com.syc.service.mapper;

import com.syc.model.entity.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.LoginLog
 */
@Repository
public interface LoginLogMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(LoginLog record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(LoginLog record);
}