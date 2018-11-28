package com.syc.service.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author choxsu
 * @date 2018/11/28
 */
public class OrdersSqlProvider {

    /**
     * 查询订单通过支付状态和订单状态
     *
     * @return
     */
    public String queryByStatus() {
        String sql = new SQL() {{
            SELECT("*").FROM("orders").WHERE("pay_status = #{payStatus} and status = #{status}");
        }}.toString();


        return sql;
    }

}
