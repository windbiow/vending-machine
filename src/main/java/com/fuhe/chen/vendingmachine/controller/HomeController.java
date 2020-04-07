package com.fuhe.chen.vendingmachine.controller;

import com.alibaba.fastjson.JSON;
import com.fuhe.chen.vendingmachine.alipay.Alipay;
import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.common.utils;
import com.fuhe.chen.vendingmachine.constant.ErrorConstant;
import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.*;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import com.fuhe.chen.vendingmachine.service.IShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    IMachineService machineService;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    IOrderService orderService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    IShoppingCartService shoppingCartService;


    /**
     * 首页
     * 根据机器id返回不同的商品信息
     * @param model
     * @param machineId
     * @return
     */
    @GetMapping({"/index/{machineId}"})
    public String index(Model model,@PathVariable int machineId){
        List<CommodityInMachine> commodity = machineService.queryCommodity(machineId);
        model.addAttribute("commodity",commodity);
        model.addAttribute("machineId",machineId);

        return "home";
    }


    @GetMapping({"/phone/index/{machineId}"})
    @ResponseBody
    public String phoneIndex(Model model,@PathVariable int machineId){
        List<CommodityInMachine> commodity = machineService.queryCommodity(machineId);
        String json=JSON.toJSONString(commodity);
        return json;
    }


    /**
     * 用户购买动作
     * 生成待支付订单
     * 返回支付链接
     * @return
     */
    @PostMapping("/buy")
    @ResponseBody
    public String buy(Model model,String commodityId,String machineId,Integer count){

        Map<CommodityInMachine,Integer> commodityMap = new HashMap<CommodityInMachine, Integer>();
        Double price = 0D;


        CommodityInMachine commodity= commodityService.queryCommodity(Integer.valueOf(commodityId),Integer.valueOf(machineId));
        commodityMap.put(commodity,count);
        price = count*commodity.getPrice();

        //自动生成订单号
        String tradeNo = utils.getOrderIdByTime();


        //添加待支付订单
        Order order = new Order();
        order.setId(tradeNo);
        order.setOrderName(tradeNo);
        order.setAmount(price);
        order.setOrderStatus(0);  //0--待支付
        order.setCreateDate(new Date().getTime());
        Machine machine = machineService.findOne(Integer.valueOf(machineId));
        order.setMachineId(machine.getId());
        order.setPlace(machine.getPlace());
        orderService.addOrder(order);

        //此处向缓存中添加已售商品信息
        commodityService.addCommoditySold(tradeNo,commodityMap);

        String orderPrice = String.valueOf(price);
        String commodityName = "贩卖机商品";
        String body = "贩卖机商品";

        //向支付宝提供订单信息并获取支付链接
        return Alipay.doPay(tradeNo,orderPrice,commodityName,body,String.valueOf(machineId));
    }


    /**
     * 用户结算购物车
     * 生成待支付订单
     * 返回支付链接
     * @return
     */
    @PostMapping("/settleShoppingCart")
    @ResponseBody
    public String settleShoppingCart(Model model, Integer machineId){

        Map<Integer,Integer> map = shoppingCartService.getShoppingCart(machineId);

        if(null==map){
            return ErrorConstant.ShoppingCart.SHOPPINGCART_IS_NULL;
        }

        Map<CommodityInMachine,Integer> commodityMap = new HashMap<CommodityInMachine, Integer>();
        Double price = 0D;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            CommodityInMachine commodity= commodityService.queryCommodity(entry.getKey(),machineId);
            commodityMap.put(commodity,entry.getValue());
            price = price + commodity.getPrice()*(entry.getValue());
        }

        //自动生成订单号
        String tradeNo = utils.getOrderIdByTime();


        //添加待支付订单
        Order order = new Order();
        order.setId(tradeNo);
        order.setOrderName(tradeNo);
        order.setAmount(price);
        order.setOrderStatus(0);  //0--待支付
        order.setCreateDate(new Date().getTime());
        Machine machine = machineService.findOne(machineId);
        order.setMachineId(machine.getId());
        order.setPlace(machine.getPlace());
        orderService.addOrder(order);

        //此处向缓存中添加已售商品信息
        commodityService.addCommoditySold(tradeNo,commodityMap);

        String orderPrice = String.valueOf(price);
        String commodityName = "贩卖机商品";
        String body = "贩卖机商品";

        //向支付宝提供订单信息并获取支付链接
        return Alipay.doPay(tradeNo,orderPrice,commodityName,body,String.valueOf(machineId));
    }


    @RequestMapping("/getNotify")
    public void getNotify(HttpServletRequest request, HttpServletResponse response){

        //支付宝验签
        boolean flag = Alipay.checkSign(request);

        if(flag){
            try{
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
                //支付时间
                String payDate = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
                //购买者支付宝id
                String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"),"UTF-8");

                //检测缓存中是否存在订单号,如果没有,说明订单已超时
                if(!redisUtils.hasKey(out_trade_no)){
                    return;
                }

                //此处向下位机发送操作指令,将商品拿出
                LOGGER.info("向下位机发送操作指令,将商品拿出");

                //订单成功处理
                orderService.orderToSuccess(out_trade_no,trade_no,payDate,buyer_id);

                //向支付宝返回success，如果不做支付宝会在24小时内再次发送6-10次回调
                PrintWriter writer = null;
                writer = response.getWriter();
                writer.write("success"); // 一定要打印success
                writer.flush();
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else
        {
            //订单未通过
        }
    }

    /**
     * 加入购物车动作
     * @return
     */
    @PostMapping("/append")
    @ResponseBody
    public String append(Model model,@RequestParam String commodityId,@RequestParam String machineId,@RequestParam Integer count){
        shoppingCartService.append(commodityId,machineId,count);
        return "添加成功";
    }

}
