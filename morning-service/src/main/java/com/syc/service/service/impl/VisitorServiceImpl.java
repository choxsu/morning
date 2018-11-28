package com.syc.service.service.impl;

import com.syc.service.dao.VisitorDao;
import com.syc.model.entity.mybatis.entity.Visitor;
import com.syc.service.service.VisitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Visitor)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Resource
    private VisitorDao visitorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Visitor queryById(Integer id) {
        return this.visitorDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Visitor> queryAllByLimit(int offset, int limit) {
        return this.visitorDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    @Override
    public Visitor insert(Visitor visitor) {
        this.visitorDao.insert(visitor);
        return visitor;
    }

    /**
     * 修改数据
     *
     * @param visitor 实例对象
     * @return 实例对象
     */
    @Override
    public Visitor update(Visitor visitor) {
        this.visitorDao.update(visitor);
        return this.queryById(visitor.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.visitorDao.deleteById(id) > 0;
    }
}