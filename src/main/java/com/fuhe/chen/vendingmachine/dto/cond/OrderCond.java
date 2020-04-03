package com.fuhe.chen.vendingmachine.dto.cond;

import lombok.Data;

@Data
public class OrderCond {
    /**
     * 订单状态 0-待支付 1-已支付 2-订单失效 3-订单退款
     */
    private Integer orderStatus;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 交易机器所在位置
     */
    private String place;

}
