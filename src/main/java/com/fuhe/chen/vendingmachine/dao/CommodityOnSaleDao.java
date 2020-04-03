package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在售商品DAO
 */
@Mapper
public interface CommodityOnSaleDao {

//    /**
//     * 新增在售商品
//     * @param commodity
//     */
//    void addCommodityOnSale(CommodityOnSale commodity);
//
    /**
     * 修改在售商品信息
     * @param commodity
     */
    void updateCommodityOnSale(CommodityOnSale commodity);

    /**
     * 删除在售商品
     * @param id
     */
    void deleteCommodityOnSale(@Param("id") Integer id);

    /**
     * 按条件查询
     * @param machineId 机器主键
     * @param commodityId 商品主键
     * @param count        数量
     * @return
     */
    List<CommodityOnSale> findByCondition(@Param("machineId") Integer machineId,@Param("commodityId") Integer commodityId, @Param("count") Integer count);

    /**
     * 查找单个在售商品信息
     * @param commodityId
     * @return
     */
    CommodityOnSale findOne(@Param("commodityId") Integer commodityId);
}
