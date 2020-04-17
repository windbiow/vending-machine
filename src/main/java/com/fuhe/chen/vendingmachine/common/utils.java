package com.fuhe.chen.vendingmachine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class utils {

    public static final Logger LOGGER = LoggerFactory.getLogger(utils.class);

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

    /**
     * 接收上传文件并返回文件相对路径
     * @param picture
     * @param imgPath
     * @return
     */
    public static String upload(MultipartFile picture,  String imgPath){
        if (picture==null){
            return imgPath;
        }else{
            try{
                String fileName = picture.getOriginalFilename();
                String filePath = ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\')+"/img/";
                File dest = new File(filePath+fileName);
                imgPath = "/static/img/"+fileName;
                picture.transferTo(dest);
                LOGGER.info("上传成功,文件路径:"+filePath+fileName);
                return imgPath;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return imgPath;
    }

    /**
     * 分割商品编号字符串
     * @param ids
     * @return
     */
    public static List<String> getOrders(String ids){
        String[] strs =ids.split(",");
        List list = Arrays.asList(strs);
        return list;
    }

    /**
     * 将"yyyy-MM-dd"字符串转化为开始或结束时间(LONG)
     * @param date
     * @param name
     * @return
     */
    public static Long getTime(String date,String name){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            if (name.equals("start")){
                Long returnNum=sdf.parse(date).getTime();
                LOGGER.info("开始时间:"+returnNum);
                return returnNum;
            }
            if (name.equals("end")){
                Date end = sdf.parse(date);
                Calendar c = Calendar.getInstance();
                c.setTime(end);
                c.add(Calendar.DAY_OF_MONTH,1);
                Long returnNum=c.getTime().getTime();
                LOGGER.info("结束时间:"+returnNum);
                return returnNum;
            }
        }catch (Exception e){
            LOGGER.info("字符串转换时间失败");
            e.printStackTrace();
        }
        return null;
    }
}