package com.fuhe.chen.vendingmachine.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 在售商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public CommodityOnSale(Integer machineId, Integer commodityId) {
        this.commodityId = commodityId;
        this.machineId = machineId;
    }

    public CommodityOnSale(Integer commodityId, Integer machineId,Integer count) {
        this.commodityId = commodityId;
        this.machineId = machineId;
        this.count=count;
    }
}
