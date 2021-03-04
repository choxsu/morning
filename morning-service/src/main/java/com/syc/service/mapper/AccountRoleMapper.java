package com.syc.service.mapper;

import com.syc.model.entity.AccountRoleKey;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.syc.model.entity.AccountRole
 */
@Repository
public interface AccountRoleMapper {
    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int deleteByPrimaryKey(AccountRoleKey key);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insert(AccountRoleKey record);

    /**
     *
     * @mbg.generated 2021-03-04 12:07:31
     */
    int insertSelective(AccountRoleKey record);
}