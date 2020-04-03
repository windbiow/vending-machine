package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 订单
 */
@Data
public class Order {

    /**
     * 主键
     */
    private String id ;

    /**
     * 订单编号
     */
    private String orderName;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 订单状态 0-待支付 1-已支付 2-订单失效 3-订单退款
     */
    private Integer orderStatus;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 购买人id
     */
    private String buyerId;

    /**
     * 支付订单号
     */
    private String payId;

    /**
     * 交易机器所在位置
     */
    private String place;

    /**
     * 支付时间
     */
    private Long payDate;

    /**
     * 订单创建时间
     */
    private Long createDate;

    /**
     * 机器主键
     */
    private Integer machineId;
}
