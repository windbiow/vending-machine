package com.fuhe.chen.vendingmachine.service.impl;

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
        if(redisUtils.hasKey("globalData")){
            Map<Object, Object> redisData = redisUtils.hmget("globalData");
            redisData.forEach((field,value)->{
                data.put((String)field,(String)value);
            });
        }else{
            String totalSalesAmount = String.valueOf(orderDao.totalSalesAmount());
            String totalSalesOrders = String.valueOf(orderDao.totalSalesOrders());
            String totalSalesCommodities = String.valueOf(commoditySoldDao.totalSalesCommodities());

            redisUtils.hset("globalData","totalSalesAmount",totalSalesAmount);
            redisUtils.hset("globalData","totalSalesOrders",totalSalesOrders);
            redisUtils.hset("globalData","totalSalesCommodities",totalSalesCommodities);

            data.put("totalSalesAmount",totalSalesAmount);
            data.put("totalSalesOrders",totalSalesOrders);
            data.put("totalSalesCommodities",totalSalesCommodities);
        }
        return data;
    }
}
