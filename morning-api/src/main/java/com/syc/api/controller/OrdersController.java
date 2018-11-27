package com.syc.api.controller;

import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.service.service.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2018-11-27 22:02:18
 */
@RestController
@RequestMapping("/api/orders/v1")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Orders selectOne(Integer id) {
        return this.ordersService.queryById(id);
    }

}