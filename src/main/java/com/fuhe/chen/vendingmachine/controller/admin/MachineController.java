package com.fuhe.chen.vendingmachine.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 机器管理首页
 * 查询机器信息/添加机器/修改机器信息/删除机器/查看机器库存/商品进货
 */
@Controller
@RequestMapping("/commodity")
public class MachineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineController.class);

}
