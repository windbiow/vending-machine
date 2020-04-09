package com.fuhe.chen.vendingmachine.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IShoppingCartService {

    /**
     * 添加购物车信息
     */
    void append(String commodityId,String machineId,Integer count);

    /**
     * 获取购物车信息
     * @param machineId
     * @return
     */
    Map<Integer, Integer> getShoppingCart(Integer machineId);

    /**
     * 清空购物车
     * @param machineId
     */
    void clear(String machineId);
}
