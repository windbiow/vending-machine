package com.fuhe.chen.vendingmachine.controller.admin;

import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * 机器管理首页
 * 查询机器信息/添加机器/修改机器信息/删除机器/查看机器库存/商品进货
 */
@Controller
@RequestMapping("/admin/machine")
public class MachineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineController.class);

    @Autowired
    IMachineService machineService;

    @Autowired
    ICommodityService commodityService;

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("machines",machineService.findAll(1,100));
        return "admin/machine-list";
    }

    @GetMapping("/detail/{machineId}")
    public String detail(Model model,@PathVariable Integer machineId){

        List<CommodityInMachine> commodity = machineService.queryCommodity(machineId);
        model.addAttribute("commodity",commodity);
        model.addAttribute("machineId",machineId);

        return "admin/machineDetail";
    }

    /**
     * 新增机器
     * @param place
     * @return
     */
    @PostMapping("/addMachine")
    @ResponseBody
    public String addMachine(@RequestParam String place){

        if(StringUtils.isEmpty(place)){
            return "地点不能为空";
        }

        Machine machine = new Machine();
        machine.setPlace(place);
        machine.setMachineStatus(0);
        machine.setStock(0);
        machineService.addMachine(machine);

        return "添加成功";
    }

    /**
     * 修改机器状态
     * @param machineId
     * @param operation
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public String update(String machineId,String operation){

        String msg = null;
        Machine machine = new Machine();
        machine.setId(Integer.parseInt(machineId));

        if(operation.equals("stop")){
            machine.setMachineStatus(1);
            msg="操作成功";
        }
        if(operation.equals("delete")){
            machine.setMachineStatus(2);
            msg="删除成功";
        }
        if(operation.equals("start")){
            machine.setMachineStatus(0);
            msg="开启";
        }
        machineService.updateMachine(machine);

        return msg;
    }

    @PostMapping("/addStock")
    @ResponseBody
    public String addStock(Integer commodityId,Integer stock,Integer machineId){
        Integer count = commodityService.queryStock(machineId,commodityId);

        commodityService.addStock(machineId,commodityId,count+stock);

        return "添加成功";
    }

}
