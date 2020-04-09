package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dao.MachineDao;
import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineServiceImpl implements IMachineService {

    @Autowired
    CommodityOnSaleDao commodityOnSaleDao;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    MachineDao machineDao;

    @Override
    public void addStock() {

    }

    @Override
    public List<CommodityInMachine> queryCommodity(Integer machineID) {
        return commodityService.queryCommodity(machineID,null,null);
    }

    @Override
    public void addMachine(Machine machine) {
        machineDao.addMachine(machine);
    }

    @Override
    public void updateMachine(Machine machine) {
        machineDao.updateMachine(machine);
    }

    @Override
    public PageInfo<Machine> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        List<Machine> machines = machineDao.findAll();
        PageInfo<Machine> pageInfo = new PageInfo<>(machines);
        return pageInfo;
    }

    @Override
    public List<Machine> findByStatus(int machineStatus, int stock) {
        return null;
    }

    @Override
    public List<Machine> findByPlace(String place) {
        return null;
    }

    @Override
    public Machine findOne(Integer machineId) {
        return machineDao.findOne(machineId);
    }
}
