package com.syc.service.service.impl;

import com.syc.service.dao.AccountRoleDao;
import com.syc.model.entity.mybatis.entity.AccountRole;
import com.syc.service.service.AccountRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AccountRole)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
@Service("accountRoleService")
public class AccountRoleServiceImpl implements AccountRoleService {
    @Resource
    private AccountRoleDao accountRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param accountid 主键
     * @return 实例对象
     */
    @Override
    public AccountRole queryById(Integer accountid) {
        return this.accountRoleDao.queryById(accountid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AccountRole> queryAllByLimit(int offset, int limit) {
        return this.accountRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param accountRole 实例对象
     * @return 实例对象
     */
    @Override
    public AccountRole insert(AccountRole accountRole) {
        this.accountRoleDao.insert(accountRole);
        return accountRole;
    }

    /**
     * 修改数据
     *
     * @param accountRole 实例对象
     * @return 实例对象
     */
    @Override
    public AccountRole update(AccountRole accountRole) {
        this.accountRoleDao.update(accountRole);
        return this.queryById(accountRole.getAccountid());
    }

    /**
     * 通过主键删除数据
     *
     * @param accountid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer accountid) {
        return this.accountRoleDao.deleteById(accountid) > 0;
    }
}