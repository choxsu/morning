package com.syc.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author su
 */
@Data
@TableName("cms_orders")
public class CmsOrders {
    /**
     * id
     */
    private Integer id;

    /**
     * user.id
     *
     */
    private Integer userId;

    /**
     * 产品id，不同的业务的产品id
     *
     */
    private Integer goodsId;

    /**
     * 订单场景 比如说不同的业务场景，通过此字段来区分
     *
     */
    private String scene;

    /**
     * 订单号
     *
     */
    private String orderSn;

    /**
     * 支付状态 0-待支付 1-已支付
     *
     */
    private Integer payStatus;

    /**
     * 订单状态 0-待付款 1-待发货 2-待收货 3-待评价 6-退款 7-订单取消 8-订单删除 9-·订单完成
     *
     */
    private Integer status;

    /**
     * 退款状态 0-默认 1-申请中 2-审核中 3-已退款 4-退款失败 5-其他
     *
     */
    private Integer refundStatus;


    /**
     * 支付时间
     *
     */
    private Integer payTime;

    /**
     * 发货时间
     *
     */
    private Integer sendTime;

    /**
     * 取消时间
     *
     */
    private Integer cancelTime;

}