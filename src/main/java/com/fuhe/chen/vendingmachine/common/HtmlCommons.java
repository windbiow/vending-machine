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
        if (status==null){
            return null;
        }
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
     * 根据数字返回订单支付方式
     * @param status
     * @return
     */
    public String getOrderPayMethod(Integer status){
        if (status==null){
            return null;
        }
        switch (status){
            case 0:
                return "支付宝";
            case 1:
                return "微信";
        }
        return null;
    }

    /**
     * 根据数字返回商品状态
     * @param status
     * @return
     */
    public String getCommodityStatus(Integer status){
        if (status==null){
            return null;
        }
        switch (status){
            case 0:
                return "正常";
            case 1:
                return "已下架";
        }
        return null;
    }

    /**
     * 根据数字返回商品状态
     * @param status
     * @return
     */
    public String getMachineStock(Integer status){
        if (status==null){
            return null;
        }
        switch (status){
            case 0:
                return "正常";
            case 1:
                return "缺货";
        }
        return null;
    }

    /**
     * 根据数字返回商品状态
     * @param status
     * @return
     */
    public String getMachineStatus(Integer status){
        if (status==null){
            return null;
        }
        switch (status){
            case 0:
                return "正常";
            case 1:
                return "停止售货";
        }
        return null;
    }


    /**
     * 返回标准格式时间
     * @param time
     * @return
     */
    public String getFormatTime(Long time){
        if (time==null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }


}
