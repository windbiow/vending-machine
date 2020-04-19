package com.fuhe.chen.vendingmachine;

import com.alipay.api.AlipayApiException;
import com.fuhe.chen.vendingmachine.alipay.Alipay;
import com.fuhe.chen.vendingmachine.common.mqtt.ServerMQTT;
import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.common.utils;
import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dao.CommoditySoldDao;
import com.fuhe.chen.vendingmachine.dao.OrderDao;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.pojo.CommoditySold;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import com.fuhe.chen.vendingmachine.wxpay.MyWXPayConfig;
import com.fuhe.chen.vendingmachine.wxpay.WXPayExample;
import com.fuhe.chen.vendingmachine.wxpay.WXSign;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class VendingMachineApplicationTests {

    @Autowired
    ServerMQTT serverMQTT;


    @Test
    void test4(){
//        try{
//            serverMQTT.sendMessage("111","helloworld");
//        }catch (Exception e){
//
//        }

    }
}
