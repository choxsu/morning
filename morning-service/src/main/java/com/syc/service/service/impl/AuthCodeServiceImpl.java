package com.syc.service.service.impl;

import com.syc.service.dao.AuthCodeDao;
import com.syc.model.entity.mybatis.entity.AuthCode;
import com.syc.service.service.AuthCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AuthCode)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
@Service("authCodeService")
public class AuthCodeServiceImpl implements AuthCodeService {
    @Resource
    private AuthCodeDao authCodeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthCode queryById(String id) {
        return this.authCodeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AuthCode> queryAllByLimit(int offset, int limit) {
        return this.authCodeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param authCode 实例对象
     * @return 实例对象
     */
    @Override
    public AuthCode insert(AuthCode authCode) {
        this.authCodeDao.insert(authCode);
        return authCode;
    }

    /**
     * 修改数据
     *
     * @param authCode 实例对象
     * @return 实例对象
     */
    @Override
    public AuthCode update(AuthCode authCode) {
        this.authCodeDao.update(authCode);
        return this.queryById(authCode.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.authCodeDao.deleteById(id) > 0;
    }
}