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
            SELECT("id, " +
                    "account_id as accountId , " +
                    "goods_id as goodsId, scene, " +
                    "order_sn as orderSn , " +
                    "pay_status as payStatus, " +
                    "status, " +
                    "refund_status as refundStatus," +
                    "created, " +
                    "pay_time as payTime, " +
                    "send_time as sendTime, " +
                    "cencel_time as cencelTime, " +
                    "delete_time as deleteTime ");
            FROM("orders");
            WHERE("pay_status = #{payStatus} and status = #{status}");
        }}.toString();


        return sql;
    }

}
