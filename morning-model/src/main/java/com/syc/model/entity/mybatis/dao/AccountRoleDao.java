package com.syc.model.entity.mybatis.dao;

import com.syc.model.entity.mybatis.entity.AccountRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AccountRole)表数据库访问层
 *
 * @author makejava
 * @since 2018-09-23 12:28:43
 */
public interface AccountRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param accountid 主键
     * @return 实例对象
     */
    AccountRole queryById(Integer accountid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AccountRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param accountRole 实例对象
     * @return 对象列表
     */
    List<AccountRole> queryAll(AccountRole accountRole);

    /**
     * 新增数据
     *
     * @param accountRole 实例对象
     * @return 影响行数
     */
    int insert(AccountRole accountRole);

    /**
     * 修改数据
     *
     * @param accountRole 实例对象
     * @return 影响行数
     */
    int update(AccountRole accountRole);

    /**
     * 通过主键删除数据
     *
     * @param accountid 主键
     * @return 影响行数
     */
    int deleteById(Integer accountid);

}