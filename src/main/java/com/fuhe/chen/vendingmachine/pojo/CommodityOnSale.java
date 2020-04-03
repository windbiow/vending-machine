package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 在售商品
 */
@Data
public class CommodityOnSale {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品主键
     */
    private Integer commodityId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 机器主键
     */
    private Integer machineId;

    /**
     * 商品信息
     */
    private Commodity commodity;


}
