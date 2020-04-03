package com.fuhe.chen.vendingmachine;

import com.alipay.api.AlipayApiException;
import com.fuhe.chen.vendingmachine.alipay.Alipay;
import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.common.utils;
import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dao.CommoditySoldDao;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.pojo.CommoditySold;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Map;

@SpringBootTest
class VendingMachineApplicationTests {

    @Autowired
    CommodityOnSaleDao commodityOnSaleDao;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CommoditySoldDao commoditySoldDao;

    @Test
    void test() throws AlipayApiException {
        redisUtils.del("4564");
    }



    @Test
    void test2() {
        Date date =new Date();
        System.out.println(date.getTime());
    }

}
