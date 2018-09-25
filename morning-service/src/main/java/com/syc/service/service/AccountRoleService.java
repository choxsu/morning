package com.syc.service.service;

import com.syc.model.entity.mybatis.entity.AccountRole;

import java.util.List;

/**
 * (AccountRole)表服务接口
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
public interface AccountRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param accountid 主键
     * @return 实例对象
     */
    AccountRole queryById(Integer accountid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AccountRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param accountRole 实例对象
     * @return 实例对象
     */
    AccountRole insert(AccountRole accountRole);

    /**
     * 修改数据
     *
     * @param accountRole 实例对象
     * @return 实例对象
     */
    AccountRole update(AccountRole accountRole);

    /**
     * 通过主键删除数据
     *
     * @param accountid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer accountid);

}