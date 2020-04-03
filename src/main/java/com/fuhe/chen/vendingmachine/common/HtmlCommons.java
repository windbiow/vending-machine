package com.fuhe.chen.vendingmachine.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HtmlCommons {


    /**
     * 根据数字返回订单状态
     * @param status
     * @return
     */
    public String getOrderStatus(Integer status){
        switch (status){
            case 0:
                return "未支付";
            case 1:
                return "已支付";
            case 2:
                return "订单失效";
            case 3:
                return "订单退款";
        }
        return null;
    }

    /**
     * 根据数字返回商品状态
     * @param status
     * @return
     */
    public String getCommodityStatus(Integer status){
        switch (status){
            case 0:
                return "正常";
            case 1:
                return "已下架";
        }
        return null;
    }

    /**
     * 返回标准格式时间
     * @param time
     * @return
     */
    public String getFormatTime(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }


}
