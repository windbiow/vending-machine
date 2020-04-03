package com.fuhe.chen.vendingmachine.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {

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
    private Integer categoryId;

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
     * 商品分类信息
     */
    private Category category;

    public Commodity(Category category) {
        this.category = category;
    }
}
