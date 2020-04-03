package com.fuhe.chen.vendingmachine.controller.admin;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        return "admin/order";
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
                    String place
    ){
        OrderCond orderCond = new OrderCond();
        orderCond.setOrderStatus(orderStatus.equals("-1")?null:Integer.valueOf(orderStatus));
        orderCond.setPayMethod(payMethod.equals("-1")?null:Integer.valueOf(payMethod));
        orderCond.setPlace(place.equals("-1")?null:place);
        PageInfo<Order> orders = orderService.findByCondition(orderCond,page==null?1:page,10);
        model.addAttribute("orders",orders);
        model.addAttribute("active","order");
        model.addAttribute("orderStatus",orderStatus);
        model.addAttribute("payMethod",payMethod);
        model.addAttribute("place",place);

        return "admin/order";
    }
}
