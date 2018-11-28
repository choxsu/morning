package com.syc.service.service;


import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.model.result.Result;
import org.springframework.boot.ApplicationArguments;

import java.util.List;

/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2018-11-27 22:02:17
 */
public interface OrdersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 订单超时处理
     *
     * @return
     */
    Result orderTimeoutAction(ApplicationArguments arguments);
}