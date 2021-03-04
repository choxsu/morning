package com.syc.service.mapper;

import com.syc.model.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.Account
 */
@Repository
public interface AccountMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(Account record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(Account record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    Account selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int updateByPrimaryKey(Account record);
}