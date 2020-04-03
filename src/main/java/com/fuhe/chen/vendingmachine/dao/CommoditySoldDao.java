package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.CommoditySold;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 已售商品DAO
 */
@Mapper
public interface CommoditySoldDao {

    /**
     * 新增已售商品
     * @param commoditySold
     */
    void addCommoditySold(CommoditySold commoditySold);

    /**
     * 查询所有
     * @return
     */
    List<CommoditySold> findAll();

    /**
     * 条件查询
     * @param name
     * @param category
     * @param orderId
     * @return
     */
    List<CommoditySold> findByCondition(String name,String category,int orderId);

    /**
     * 查询销售总商品数
     * @return
     */
    Integer totalSalesCommodities();
}
