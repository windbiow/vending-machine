package com.fuhe.chen.vendingmachine.service;

import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.Category;
import com.fuhe.chen.vendingmachine.pojo.Commodity;
import com.fuhe.chen.vendingmachine.pojo.CommoditySold;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 商品服务层
 */
public interface ICommodityService {

    /**
     * 根据在售商品主键查询商品
     * @return CommodityInMachine 在售商品信息
     */
    CommodityInMachine queryCommodity(Integer commodityId,Integer machineId);


    /**
     * 按条件查询商品信息
     * @param machineID 机器id
     * @param commodityID 商品id
     * @param count  数量
     * @return
     */
    List<CommodityInMachine> queryCommodity(Integer machineID, Integer commodityID, Integer count);

    /**
     * 将待支付订单的商品信息加入缓存中
     * @param commoditySolds
     */
    void addCommoditySold(String tradeNo,Map<CommodityInMachine,Integer> commoditySolds,String machineId);

    /**
     * 查询商品信息(分页)
     * @param pageNum
     * @param size
     * @return
     */
    PageInfo<Commodity> queryAll(int pageNum, int size);

    /**
     * 查询单个商品信息
     * @param id
     * @return
     */
    Commodity queryOne(Integer id);

    /**
     * 添加商品信息
     * @param commodity
     */
    void addCommodity(Commodity commodity);

    /**
     * 修改商品信息
     * @param commodity
     */
    void updateCommodity(Commodity commodity);

    /**
     * 删除商品信息
     * @param commodityId
     */
    void deleteCommodity(Integer commodityId);

    /**
     * 查询所有标签信息
     */
    List<Category> findAllCategories();


    /**
     * 查询库存
     */
    Integer queryStock(Integer machineID, Integer commodityID);

    /**
     * 商品进货
     * 增加库存
     */
    void addStock(Integer machineID, Integer commodityID,Integer count);
}
