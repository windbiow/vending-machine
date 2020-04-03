package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 商品分类
 */

@Data
public class Category {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;
}
