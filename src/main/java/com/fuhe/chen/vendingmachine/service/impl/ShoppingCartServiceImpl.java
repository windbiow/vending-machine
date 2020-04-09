package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.controller.admin.CommodityController;
import com.fuhe.chen.vendingmachine.service.IShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ShoppingCartServiceImpl  implements IShoppingCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void append(String commodityId, String machineId, Integer count) {

        if(redisUtils.hHasKey("shoppingCart"+machineId,commodityId)){
            redisUtils.hincr("shoppingCart"+machineId,commodityId,count);
        }else{
            redisUtils.hset("shoppingCart"+machineId,commodityId,count);
        }
    }

    @Override
    public Map<Integer, Integer> getShoppingCart(Integer machineId) {
        Map<Integer, Integer> returnMap = new HashMap<>();

        if(redisUtils.hasKey("shoppingCart"+machineId)){
            redisUtils.hmget("shoppingCart"+machineId).forEach((key,value)->{
                returnMap.put(Integer.parseInt(key.toString()),Integer.parseInt(value.toString()));
            });
            return returnMap;
        }

        return null;
    }

    @Override
    public void clear(String machineId) {
        redisUtils.del("shoppingCart"+machineId);
        LOGGER.info("机器id:"+machineId+"的购物车记录已清空");
    }

}
