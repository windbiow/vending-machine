package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品DAO
 */
@Mapper
public interface CommodityDao {

    /**
     * 新增商品
     * @param commodity
     */
    void addCommodity(Commodity commodity);

    /**
     * 修改商品信息
     * @param commodity
     */
    void updateCommodity(Commodity commodity);

    /**
     * 删除商品
     * @param id
     */
    void deleteCommodity(@Param("id") Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Commodity> findAll();

    /**
     * 按条件查询
     * @param name
     * @param categoryId
     * @param commodityStatus
     * @return
     */
    List<Commodity> findByCondition(String name,int categoryId,int commodityStatus);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    Commodity findOne(@Param("id") Integer id);


}
