package com.fuhe.chen.vendingmachine.common;

import com.fuhe.chen.vendingmachine.pojo.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
}