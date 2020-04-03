package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ShoppingCartServiceImpl  implements IShoppingCartService {

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

}
