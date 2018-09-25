package com.syc.service.service;

import com.syc.model.entity.mybatis.entity.RolePermission;

import java.util.List;

/**
 * (RolePermission)表服务接口
 *
 * @author makejava
 * @since 2018-09-25 12:55:23
 */
public interface RolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    RolePermission queryById(Integer roleid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RolePermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    RolePermission insert(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    RolePermission update(RolePermission rolePermission);

    /**
     * 通过主键删除数据
     *
     * @param roleid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleid);

}