package com.syc.model.entity.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.syc.model.entity.mybatis.entity.SensitiveWords;

/**
 * (SensitiveWords)表数据库访问层
 *
 * @author makejava
 * @since 2018-09-23 12:28:45
 */
public interface SensitiveWordsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SensitiveWords queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SensitiveWords> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sensitiveWords 实例对象
     * @return 对象列表
     */
    List<SensitiveWords> queryAll(SensitiveWords sensitiveWords);

    /**
     * 新增数据
     *
     * @param sensitiveWords 实例对象
     * @return 影响行数
     */
    int insert(SensitiveWords sensitiveWords);

    /**
     * 修改数据
     *
     * @param sensitiveWords 实例对象
     * @return 影响行数
     */
    int update(SensitiveWords sensitiveWords);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}