package com.syc.model.entity.mybatis.entity;

import java.io.Serializable;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2018-11-27 22:02:16
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 240608897324108800L;
    
    private Integer id;
    //account.id
    private Integer accountId;
    //产品id，不同的业务的产品id
    private Integer goodsId;
    //订单场景 比如说不同的业务场景，通过此字段来区分
    private String scene;
    //订单号
    private String orderSn;
    //支付状态 0-待支付 1-已支付
    private Integer payStatus;
    //订单状态 0-待付款 1-待发货 2-待收货 3-待评价 6-退款 7-订单取消 8-订单删除
    private Integer status;
    //退款状态 0-默认 1-申请中 2-审核中 3-已退款 4-退款失败 5-其他
    private Integer refundStatus;
    //创建时间
    private Integer created;
    //支付时间
    private Integer payTime;
    //发货时间
    private Integer sendTime;
    //取消时间
    private Integer cencelTime;
    //删除时间
    private Integer deleteTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getPayTime() {
        return payTime;
    }

    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getCencelTime() {
        return cencelTime;
    }

    public void setCencelTime(Integer cencelTime) {
        this.cencelTime = cencelTime;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

}