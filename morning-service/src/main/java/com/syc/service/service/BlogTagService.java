package com.syc.service.service;

import com.syc.model.entity.mybatis.entity.BlogTag;

import java.util.List;


/**
 * 标签表(BlogTag)表服务接口
 *
 * @author makejava
 * @since 2018-09-25 12:26:16
 */
public interface BlogTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogTag queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BlogTag> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    BlogTag insert(BlogTag blogTag);

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    BlogTag update(BlogTag blogTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}