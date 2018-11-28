package com.syc.service.service.impl;

import com.syc.service.dao.BlogTagDao;
import com.syc.model.entity.mybatis.entity.BlogTag;
import com.syc.service.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 标签表(BlogTag)表服务实现类
 *
 * @author makejava
 * @since 2018-09-25 12:26:16
 */
@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {
    @Resource
    private BlogTagDao blogTagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogTag queryById(Integer id) {
        return this.blogTagDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BlogTag> queryAllByLimit(int offset, int limit) {
        List<BlogTag> blogTags = this.blogTagDao.queryAllByLimit(offset, limit);
        return blogTags;
    }

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTag insert(BlogTag blogTag) {
        this.blogTagDao.insert(blogTag);
        return blogTag;
    }

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTag update(BlogTag blogTag) {
        this.blogTagDao.update(blogTag);
        return this.queryById(blogTag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogTagDao.deleteById(id) > 0;
    }
}