package com.fuhe.chen.vendingmachine.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fuhe.chen.vendingmachine.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.alipay.api.AlipayConstants.*;

/**
 * 支付宝静态方法
 */
@Component
public class Alipay {

    private static final Logger LOGGER = LoggerFactory.getLogger(Alipay.class);

    /**
     * 调用支付宝网页支付接口
     * @param outTradeNo
     * @param totalAmount
     * @param subject
     * @param body
     * @param machineId
     * @return
     */
    public static String doPay(String outTradeNo,String totalAmount,String subject,String body,String machineId){

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setReturnUrl(AlipayConfig.return_url+machineId);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try{
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            LOGGER.info(result);
            return result;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 调用支付宝预支付接口
     * @param outTradeNo
     * @param totalAmount
     * @param subject
     * @param body
     * @param machineId
     * @return
     */
    public static String doPay2(String outTradeNo,String totalAmount,String subject,String body,String machineId){

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        //设置请求参数
        AlipayTradePrecreateRequest  alipayRequest = new AlipayTradePrecreateRequest ();
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try{
            String result = alipayClient.execute(alipayRequest).getBody();
            LOGGER.info(result);
            return result;
        }catch (Exception e){

        }
        return null;
    }


    /**
     * 支付宝的验签方法
     * @param req
     * @return
     */
    public static boolean checkSign(HttpServletRequest req) {
        Map<String, String[]> requestMap = req.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            LOGGER.info(("key值为"+key+"value为："+strs));
            paramsMap.put(key, strs);
        });

        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.info("*********************验签失败********************");
            return false;
        }
    }

    /**
     * 查询支付宝订单结果
     * @param out_trade_no
     * @param trade_no
     * @param org_pid
     * @param query_options
     * @return
     */
    public static AlipayTradeQueryResponse queryTrade(String out_trade_no,String trade_no,String org_pid,String query_options){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+out_trade_no+"\"," +
                "\"trade_no\":\""+trade_no+"\"," +
                "\"org_pid\":\""+org_pid+"\"," +
                "      \"query_options\":[" +
                "        \""+query_options+"\"" +
                "      ]" +
                "  }");
        try{
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            LOGGER.info("\n"+
                    "买家支付宝账号:"+response.getBuyerLogonId()+"\n"+
                    "交易状态:"+response.getTradeStatus()+"\n"+
                    "订单金额:"+response.getTotalAmount()+"\n"+
                    "本次交易打款给卖家的时间:"+response.getSendPayDate()+"\n"+
                    "实收金额:"+response.getReceiptAmount()
            );
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}