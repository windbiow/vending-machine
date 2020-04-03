package com.fuhe.chen.vendingmachine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommodityInMachine {
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
     * 商品分类
     */
    private String categoryName;

    /**
     * 商品状态
     */
    private Integer commodityStatus;

    /**
     * 图片
     */
    private String picture;

    /**
     * 备注
     */
    private String comment;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 机器主键
     */
    private Integer machineId;

    public CommodityInMachine(Integer id, String commodityName, Double price, String categoryName, Integer commodityStatus, String picture, String comment, Integer count, Integer machineId) {
        this.id = id;
        this.commodityName = commodityName;
        this.price = price;
        this.categoryName = categoryName;
        this.commodityStatus = commodityStatus;
        this.picture = picture;
        this.comment = comment;
        this.count = count;
        this.machineId = machineId;
    }
}
