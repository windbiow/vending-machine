package com.fuhe.chen.vendingmachine.controller.admin;

import com.fuhe.chen.vendingmachine.alipay.Alipay;
import com.fuhe.chen.vendingmachine.common.utils;
import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import com.fuhe.chen.vendingmachine.service.IStatisticService;
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

/**
 * 后台管理首页
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IStatisticService statisticService;

    @GetMapping({"/","/index",""})
    public String index(Model model){
        model.addAllAttributes(statisticService.globalData());
        return "admin/index1";
    }
}
