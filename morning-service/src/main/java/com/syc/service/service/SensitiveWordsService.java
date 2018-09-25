package com.syc.service.service;

import com.syc.model.entity.mybatis.entity.SensitiveWords;

import java.util.List;

/**
 * (SensitiveWords)表服务接口
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
public interface SensitiveWordsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SensitiveWords queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SensitiveWords> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    SensitiveWords insert(SensitiveWords sensitiveWords);

    /**
     * 修改数据
     *
     * @param sensitiveWords 实例对象
     * @return 实例对象
     */
    SensitiveWords update(SensitiveWords sensitiveWords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}