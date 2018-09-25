package com.syc.service.service;

import com.syc.model.entity.mybatis.entity.AuthCode;

import java.util.List;

/**
 * (AuthCode)表服务接口
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
public interface AuthCodeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthCode queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AuthCode> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param authCode 实例对象
     * @return 实例对象
     */
    AuthCode insert(AuthCode authCode);

    /**
     * 修改数据
     *
     * @param authCode 实例对象
     * @return 实例对象
     */
    AuthCode update(AuthCode authCode);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}