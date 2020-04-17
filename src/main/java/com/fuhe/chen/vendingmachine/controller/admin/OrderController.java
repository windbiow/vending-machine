package com.fuhe.chen.vendingmachine.controller.admin;

import com.fuhe.chen.vendingmachine.common.utils;
import com.fuhe.chen.vendingmachine.dto.cond.OrderCond;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 账单管理首页
 * 1.账单查询
 */
@Controller
@RequestMapping("/admin/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IOrderService orderService;

    /**
     * 账单管理首页
     * @param model
     * @return
     */
    @GetMapping({""})
    public String getOrderList(
            Model model
    ){
        PageInfo<Order> orders = orderService.findAll(1,10);
        model.addAttribute("orders",orders);
        model.addAttribute("active","order");
        model.addAttribute("orderStatus","-1");
        model.addAttribute("payMethod","-1");
        model.addAttribute("place","-1");
        return "admin/order-list";
    }

    /**
     * 账单管理翻页
     * @param model
     * @param page
     * @return
     */
    @RequestMapping({"/query","/query/page/{page}"})
    public String turnPage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @PathVariable(name = "page", required = false,value ="page")
                    Integer page,
            @RequestParam(name = "orderStatus", required = false)
                    String orderStatus,
            @RequestParam(name = "payMethod", required = false)
                    String payMethod,
            @RequestParam(name = "place", required = false)
                    String place,
            @RequestParam(name = "start", required = false)
                    String start,
            @RequestParam(name = "end", required = false)
                    String end,
            @RequestParam(name = "trade_no", required = false)
                    String trade_no
    ){
        //添加查询条件
        OrderCond orderCond = new OrderCond();
        orderCond.setOrderStatus(orderStatus.equals("-1")?null:Integer.valueOf(orderStatus));
        orderCond.setPayMethod(payMethod.equals("-1")?null:Integer.valueOf(payMethod));
        orderCond.setPlace(place.equals("-1")?null:place);
            if(!start.equals("null")&&!StringUtils.isEmpty(start)){
                orderCond.setStart(utils.getTime(start,"start"));
                model.addAttribute("start",start);
            }
            if(!end.equals("null")&&!StringUtils.isEmpty(end)){
                orderCond.setEnd(utils.getTime(end,"end"));
                model.addAttribute("end",end);
            }
        if(!trade_no.equals("null")&&!StringUtils.isEmpty(trade_no)){
            orderCond.setTrade_no(trade_no);
            model.addAttribute("trade_no",trade_no);
        }


        PageInfo<Order> orders = orderService.findByCondition(orderCond,page==null?1:page,10);

        //返回时携带来时的查询条件
        model.addAttribute("orders",orders);
        model.addAttribute("orderStatus",orderStatus);
        model.addAttribute("payMethod",payMethod);
        model.addAttribute("place",place);


        return "admin/order-list";
    }

    @GetMapping("/detail/{orderName}")
    public String orderDetail(@PathVariable String orderName,Model model){
        Order order = orderService.queryOrder(orderName);
        model.addAttribute("order",order);
        return "admin/order-detail";
    }

    @ResponseBody
    @PostMapping("/delAll")
    public String delAll(@RequestParam(required = true) String ids){
        List list = utils.getOrders(ids);
        orderService.delAll(list);
        return "success";
    }

    @ResponseBody
    @PostMapping("/delete")
    public String delete(@RequestParam(required = true)String orderId){
        orderService.delete(orderId);
        return "success";
    }
}
