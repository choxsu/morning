package com.syc.service.service.impl;

import com.syc.service.dao.OrdersDao;
import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.model.result.Result;
import com.syc.service.scheduled.OrderTimeoutScheduled;
import com.syc.service.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2018-11-27 22:03:58
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;

    @Autowired
    private OrderTimeoutScheduled orderTimeoutScheduled;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(Integer id) {
        return this.ordersDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Orders> queryAllByLimit(int offset, int limit) {
        return this.ordersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders) {
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.ordersDao.deleteById(id) > 0;
    }

    /**
     * 订单超时处理
     * @param arguments
     * @return
     */
    @Override
    public Result orderTimeoutAction(ApplicationArguments arguments) {

        List<Orders> orders = this.ordersDao.queryByStatus(0, 0);


        Result result = orderTimeoutScheduled.timeoutAction(orders);

        return Result.fail().setMsg("订单处理失败！");
    }
}