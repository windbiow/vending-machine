package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.dao.CategoryDao;
import com.fuhe.chen.vendingmachine.dao.CommodityDao;
import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import com.fuhe.chen.vendingmachine.pojo.Category;
import com.fuhe.chen.vendingmachine.pojo.Commodity;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.service.ICommodityService;
import com.fuhe.chen.vendingmachine.service.IMachineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class CommodityServiceImpl implements ICommodityService {

    @Autowired
    IMachineService machineService;

    @Autowired
    CommodityOnSaleDao commodityOnSaleDao;

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public CommodityInMachine queryCommodity(Integer commodityId) {
        return queryCommodity(null,commodityId,null).get(0);
    }

    @Override
    public List<CommodityInMachine> queryCommodity(Integer machineID,Integer commodityID,Integer count) {
        List<CommodityOnSale> list= commodityOnSaleDao.findByCondition(machineID,commodityID,count);
        List<CommodityInMachine> commodity= list.stream().map(commodityOnSale -> new CommodityInMachine(
                commodityOnSale.getId(),
                commodityOnSale.getCommodity().getCommodityName(),
                commodityOnSale.getCommodity().getPrice(),
                commodityOnSale.getCommodity().getCategory().getCategoryName(),
                commodityOnSale.getCommodity().getCommodityStatus(),
                commodityOnSale.getCommodity().getPicture(),
                commodityOnSale.getCommodity().getComment(),
                commodityOnSale.getCount(),
                commodityOnSale.getMachineId())).collect(Collectors.toList());

        return commodity;
    }

    @Override
    public void addCommoditySold(String tradeNo, Map<CommodityInMachine, Integer> commoditySolds) {
        commoditySolds.forEach((commodity,count)->{
            String field = String.valueOf(commodity.getId());
            String value = commodity.getPrice()+"_"+count+"_"+commodity.getCategoryName()+"_"+commodity.getCommodityName();
            redisUtils.hset(tradeNo,field,value);
            redisUtils.expire(tradeNo,60*15);
        });
    }

    @Override
    public PageInfo<Commodity> queryAll(int pageNum, int size) {
        PageHelper .startPage(pageNum,size);
        List<Commodity> commodities = commodityDao.findAll();
        PageInfo<Commodity> commodityPageInfo=new PageInfo<>(commodities);

        return commodityPageInfo;
    }

    @Override
    public Commodity queryOne(Integer id) {
        return commodityDao.findOne(id);
    }

    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.addCommodity(commodity);
    }

    @Override
    public void updateCommodity(Commodity commodity) {
        commodityDao.updateCommodity(commodity);
    }

    @Override
    @Transactional
    public void deleteCommodity(Integer commodityId) {
        commodityDao.deleteCommodity(commodityId);
        commodityOnSaleDao.deleteCommodityOnSale(commodityId);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAll();
    }
}
