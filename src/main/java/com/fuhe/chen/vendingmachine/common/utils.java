package com.fuhe.chen.vendingmachine.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class utils {

    static Integer count = 0;
    static String preDate = null;

    /**
     * 自动生成16位订单号
     *
     * @return
     */
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        count = count + 1;
//        String sequence = String.format("%0" + 4 + "d", count);

        if (preDate == null) {
            preDate = newDate;
        }
        if (!preDate.equals(newDate)) {
            preDate = newDate;
            count = 0;
        }

        String result = "FH";
        return result + newDate ;//+ sequence; //"FH20200328200001"
    }

    public static Map<Integer, Integer> getCommodities(String commodities) {
        Map<Integer, Integer> map= new HashMap<>();
        String[] strs = commodities.split(":");
        for(int i=0;i<strs.length;i++){
            String [] str = strs[i].split("_");
            Integer commodity = new Integer(str[0]);
            Integer count = new Integer(str[1]);
            map.put(commodity,count);
        }
        return map;

    }
}