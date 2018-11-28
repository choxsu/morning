package com.syc.service.sql;

/**
 * @author choxsu
 * @date 2018/11/28
 */
public class OrdersSqlProvider {

    /**
     * 查询订单通过支付状态和订单状态
     * @return
     */
    public String queryByStatus(){
        return "select * from orders where pay_status = #{payStatus} and status = #{status}";
    }

}
