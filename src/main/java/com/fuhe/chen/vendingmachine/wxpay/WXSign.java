package com.fuhe.chen.vendingmachine.wxpay;

import com.alibaba.fastjson.JSONObject;
import com.fuhe.chen.vendingmachine.common.HttpService;
import com.sun.jndi.toolkit.url.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class WXSign {

    public static final Logger LOGGER = LoggerFactory.getLogger(WXSign.class);

    public static String GetSignKey()
    {
//        String nonceStr = WxPayApi.GenerateNonceStr();
//        WxPayData signParam = new WxPayData();
        Map<String,String> map = new HashMap<>();
        map.put("mch_id", "1305638280");
        map.put("nonce_str", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        map.put("sign", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        String param =  mapToXml(map);
        String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
        String response = HttpService.sendPost_body(url, param);
        LOGGER.debug("GetSignKey response : " + response);
        return response;
//        WxPayData result = new WxPayData();
//        SortedDictionary<string, object> items = result.FromXml(response);
//        object signKey;
//        if (items.TryGetValue("sandbox_signkey", out signKey))
//        {
//            return signKey.ToString();
//        }
//        else if (items.TryGetValue("return_msg", out signKey))
//        {
//            throw new WxPayException(signKey.ToString());
//        }
//        else
//            throw new WxPayException("获取沙箱密钥失败！");
    }

    public static String mapToXml(Map<String, String> data)  {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.newDocument();
            org.w3c.dom.Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key: data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                org.w3c.dom.Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
            writer.close();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
