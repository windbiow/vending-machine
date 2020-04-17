package com.fuhe.chen.vendingmachine.controller.admin;

import com.fuhe.chen.vendingmachine.service.IStatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "admin/index";
    }
}
