package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 已售商品
 */
@Data
public class CommoditySold {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 价格
     */
    private Double price;

    /**
     * 数量
     */
    private Integer count;


    /**
     * 商品分类名称
     */
    private String category;

    /**
     * 订单主键
     */
    private String OrderId;
}
