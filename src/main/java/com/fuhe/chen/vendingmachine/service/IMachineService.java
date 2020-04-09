package com.fuhe.chen.vendingmachine.service;

import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.Commodity;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机器管理服务层
 */

public interface IMachineService {

    /**
     * 商品进货
     */
    void addStock();

    /**
     * 查询本机所有商品
     * @param machineID 机器主键
     * @return
     */
    List<CommodityInMachine> queryCommodity(Integer machineID);

    /**
     * 新增机器
     * @param machine
     */
    void addMachine(Machine machine);

    /**
     * 修改机器信息
     * @param machine
     */
    void updateMachine(Machine machine);

    /**
     * 查询所有机器
     * @return
     */
    PageInfo<Machine> findAll(int page, int size);

    /**
     * 根据机器的运行状态和库存状态查询机器
     * @param machineStatus
     * @param stock
     * @return
     */
    List<Machine> findByStatus(int machineStatus,int stock);

    /**
     * 根据机器所在地模糊查询
     * @param place
     * @return
     */
    List<Machine> findByPlace(String place);

    /**
     * 根据机器id查询
     * @param machineId 机器id
     * @return
     */
    Machine findOne(Integer machineId);

}
