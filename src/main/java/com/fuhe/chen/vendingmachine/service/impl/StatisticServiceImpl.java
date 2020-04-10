package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisConstant;
import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.dao.CommoditySoldDao;
import com.fuhe.chen.vendingmachine.dao.OrderDao;
import com.fuhe.chen.vendingmachine.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CommoditySoldDao commoditySoldDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public Map<String, String> globalData() {
        Map<String, String> data = new HashMap<>();
//        如果redis中有直接从redis中获取
        if(redisUtils.hasKey(RedisConstant.GlobalData.GLOBALDATA)){
            Map<Object, Object> redisData = redisUtils.hmget(RedisConstant.GlobalData.GLOBALDATA;
            redisData.forEach((field,value)->{
                data.put((String)field,(String)value);
            });
        }else{
            String totalSalesAmount = String.valueOf(orderDao.totalSalesAmount());
            String totalSalesOrders = String.valueOf(orderDao.totalSalesOrders());
            String totalSalesCommodities = String.valueOf(commoditySoldDao.totalSalesCommodities());

            redisUtils.hset(RedisConstant.GlobalData.GLOBALDATA,RedisConstant.GlobalData.TOTALSALESAMOUNT,totalSalesAmount);
            redisUtils.hset(RedisConstant.GlobalData.GLOBALDATA,RedisConstant.GlobalData.TOTALSALESORDERS,totalSalesOrders);
            redisUtils.hset(RedisConstant.GlobalData.GLOBALDATA,RedisConstant.GlobalData.TOTALSALESCOMMODITIES,totalSalesCommodities);

            data.put(RedisConstant.GlobalData.TOTALSALESAMOUNT,totalSalesAmount);
            data.put(RedisConstant.GlobalData.TOTALSALESORDERS,totalSalesOrders);
            data.put(RedisConstant.GlobalData.TOTALSALESCOMMODITIES,totalSalesCommodities);
        }
        return data;
    }
}
