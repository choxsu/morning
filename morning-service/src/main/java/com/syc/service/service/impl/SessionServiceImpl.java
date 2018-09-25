package com.syc.service.service.impl;

import com.syc.model.entity.mybatis.dao.SessionDao;
import com.syc.model.entity.mybatis.entity.Session;
import com.syc.service.service.SessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Session)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {
    @Resource
    private SessionDao sessionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Session queryById(String id) {
        return this.sessionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Session> queryAllByLimit(int offset, int limit) {
        return this.sessionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session insert(Session session) {
        this.sessionDao.insert(session);
        return session;
    }

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session update(Session session) {
        this.sessionDao.update(session);
        return this.queryById(session.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.sessionDao.deleteById(id) > 0;
    }
}