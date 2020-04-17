package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dao.MachineDao;
import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.pojo.Machine;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineServiceImpl implements IMachineService {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final static String MACHINENAME_ALL="machineAll:";
    @Autowired
    CommodityOnSaleDao commodityOnSaleDao;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    RedisUtils redisUtils;

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
        redisUtils.delAll(MACHINENAME_ALL);
    }

    @Override
    public void updateMachine(Machine machine) {
        machineDao.updateMachine(machine);
        redisUtils.delAll(MACHINENAME_ALL);
    }

    @Override
    public PageInfo<Machine> findAll(int page,int size) {
        String key = MACHINENAME_ALL+page+":"+size;
        if(redisUtils.hasKey(key)){
            return (PageInfo<Machine>)redisUtils.get(key);
        }else{
            PageHelper.startPage(page,size);
            List<Machine> machines = machineDao.findAll();
            PageInfo<Machine> pageInfo = new PageInfo<>(machines);
            redisUtils.set(key,pageInfo);
            return pageInfo;
        }
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
