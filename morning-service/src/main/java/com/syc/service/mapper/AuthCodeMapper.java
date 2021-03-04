package com.syc.service.mapper;

import com.syc.model.entity.AuthCode;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.AuthCode
 */
@Repository
public interface AuthCodeMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(AuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(AuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    AuthCode selectByPrimaryKey(String id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKeySelective(AuthCode record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKey(AuthCode record);
}