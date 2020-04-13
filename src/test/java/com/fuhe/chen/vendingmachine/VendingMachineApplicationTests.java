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


    @Test
    void test3(){
        System.out.println(Alipay.queryTrade("FH20200331143336","2020033122001421370501101803",null,null));
    }


    @Test
    void test2(){
        System.out.println(WXSign.GetSignKey());
    }

    @Test
    void test(){
        try {
            MyWXPayConfig config = new MyWXPayConfig();
            WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, true);


            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "腾讯充值中心-QQ会员充值");
            data.put("out_trade_no", "2016090910595900000012");
            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "123.12.12.123");
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            data.put("product_id", "12");


                Map<String, String> resp = wxpay.unifiedOrder(data);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
