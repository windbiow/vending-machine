package com.fuhe.chen.vendingmachine.controller.admin;

import com.fuhe.chen.vendingmachine.dao.CategoryDao;
import com.fuhe.chen.vendingmachine.dao.CommodityDao;
import com.fuhe.chen.vendingmachine.pojo.Category;
import com.fuhe.chen.vendingmachine.pojo.Commodity;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品管理首页
 * 新增商品/修改商品信息/删除商品信息
 */
@Controller
@RequestMapping("/admin/commodity")
public class CommodityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    ICommodityService commodityService;


    @RequestMapping("")
    public String commodity(Model model){
        PageInfo<Commodity> commodities = commodityService.queryAll(1,10);
        model.addAttribute("commodities",commodities);
        return "admin/commodity";
    }

    @RequestMapping("/add")
    public String add(Model model){

        model.addAttribute("commodity",new Commodity(new Category()));
        model.addAttribute("categories",commodityService.findAllCategories());
        return "admin/commodityDetail";
    }

    @RequestMapping("/update/{id}")
    public String update(Model model,@PathVariable Integer id){

        Commodity commodity = commodityService.queryOne(id);
        model.addAttribute("commodity",commodity);
        model.addAttribute("categories",commodityService.findAllCategories());

        return "admin/commodityDetail";
    }

    @RequestMapping("/addComm")
    @ResponseBody
    public String addComm(String commodityName,String categoryId,String price){

        Commodity commodity = new Commodity();
        commodity.setCommodityName(commodityName);
        commodity.setCategoryId(Integer.parseInt(categoryId));
        commodity.setCommodityStatus(0);
        commodity.setPrice(Double.parseDouble(price));

        commodityService.addCommodity(commodity);

        return "添加成功";
    }

    @RequestMapping("/updateComm")
    @ResponseBody
    public String updateComm(String commodityId,String commodityName,String categoryId,String price){

        Commodity commodity = new Commodity();
        commodity.setId(Integer.parseInt(commodityId));
        commodity.setCommodityName(commodityName);
        commodity.setCategoryId(Integer.parseInt(categoryId));
        commodity.setCommodityStatus(0);
        commodity.setPrice(Double.parseDouble(price));

        commodityService.updateCommodity(commodity);

        return "修改成功";
    }

    @RequestMapping("/deleteComm")
    @ResponseBody
    public String deleteComm(String commodityId){

        commodityService.deleteCommodity(Integer.valueOf(commodityId));

        return "删除成功";
    }


}
