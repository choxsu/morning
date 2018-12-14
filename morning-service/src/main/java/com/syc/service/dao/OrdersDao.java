package com.syc.service.dao;

//import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.service.sql.OrdersSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * (Orders)表数据库访问层
 *
 * @author makejava
 * @since 2018-11-27 22:02:17
 */
public interface OrdersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    //Orders queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    //List<Orders> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orders 实例对象
     * @return 对象列表
     */
    //List<Orders> queryAll(Orders orders);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
//    int insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
//    int update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询订单通过支付状态和订单状态
     *
     * @param payStatus 支付状态
     * @param status    订单状态
     * @return
     */
//    @SelectProvider(type = OrdersSqlProvider.class, method = "queryByStatus")
//    @ResultMap("OrdersMap")
//    List<Orders> queryByStatus(@Param("payStatus") int payStatus, @Param("status") int status);

//    @Update("update orders SET status = #{status} ,cancel_time = UNIX_TIMESTAMP()  where id = #{id}")
//    int updateStatusById(@Param("status") Integer status,@Param("id") Integer id);
}