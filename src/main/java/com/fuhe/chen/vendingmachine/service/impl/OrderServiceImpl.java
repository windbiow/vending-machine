package com.fuhe.chen.vendingmachine.service.impl;

import com.fuhe.chen.vendingmachine.common.redis.RedisConstant;
import com.fuhe.chen.vendingmachine.common.redis.RedisUtils;
import com.fuhe.chen.vendingmachine.dao.CommodityOnSaleDao;
import com.fuhe.chen.vendingmachine.dao.CommoditySoldDao;
import com.fuhe.chen.vendingmachine.dao.OrderDao;
import com.fuhe.chen.vendingmachine.dto.cond.OrderCond;
import com.fuhe.chen.vendingmachine.pojo.CommodityOnSale;
import com.fuhe.chen.vendingmachine.pojo.CommoditySold;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.fuhe.chen.vendingmachine.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CommodityOnSaleDao commodityOnSaleDao;

    @Autowired
    CommoditySoldDao commoditySoldDao;

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public PageInfo<Order> findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderDao.findAll();
        PageInfo<Order> pageInfo = new PageInfo<>(orders);

        return pageInfo;
    }

    @Override
    public PageInfo<Order> findByCondition(OrderCond cond, int pageNum, int size) {
        PageHelper.startPage(pageNum,size);
        List<Order> orders = orderDao.findByCondition(
                cond.getOrderStatus(),
                cond.getPayMethod(),
                cond.getPlace(),
                cond.getStart(),
                cond.getEnd(),
                cond.getTrade_no());
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        return pageInfo;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    /**
     * 支付成功处理
     * @param out_trade_no 商户订单号
     * @param trade_no      支付宝交易号
     * @param payDate  支付时间
     * @param buyer_id      购买者id
     * @throws Exception
     */
    @Transactional
    @Override
    public void orderToSuccess(String out_trade_no, String trade_no, String payDate, String buyer_id) throws Exception{
        
        //修改订单状态,添加订单支付时间,订单支付人信息,支付方式,支付交易号
        Order order = new Order();
        order.setId(out_trade_no);
        order.setPayDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(payDate).getTime());
        order.setBuyerId(buyer_id);
        order.setPayMethod(0);
        order.setPayId(trade_no);
        order.setOrderStatus(1);
        orderDao.updateOrder(order);

        Map<Object,Object> redisCommodity= redisUtils.hmget(out_trade_no);
        redisCommodity.forEach((commodityId,commodityStr)->{
            //修改在售商品信息,商品库存减量
            CommodityOnSale commodity = commodityOnSaleDao.findOne(Integer.parseInt(commodityId.toString()));

            String [] str = ((String)commodityStr).split("_");
            commodity.setCommodityId(commodity.getId());
            commodity.setCount(commodity.getCount()-Integer.valueOf(str[1]));
            commodityOnSaleDao.updateCommodityOnSale(commodity);

            //添加已售商品信息
            CommoditySold commoditySold = new CommoditySold();
            commoditySold.setCommodityName(str[3]);
            commoditySold.setOrderId(out_trade_no);
            commoditySold.setPrice(Double.valueOf(str[0]));
            commoditySold.setCount(Integer.valueOf(str[1]));
            commoditySold.setCategory(str[2]);
            commoditySoldDao.addCommoditySold(commoditySold);
        });
        Integer machineId = orderDao.findMachineId(out_trade_no);

        //删除缓存信息
        redisUtils.del(out_trade_no);
        redisUtils.del(RedisConstant.GlobalData.GLOBALDATA);
        redisUtils.del(RedisConstant.ShoppingCart.SHOPPINGCART+machineId);

    }


    @Override
    public void delAll(List<String> orders) {
        orderDao.delAll(orders);
        commoditySoldDao.delAll(orders);
    }

    @Override
    public void delete(String orderId) {
        orderDao.delete(orderId);
        commoditySoldDao.delete(orderId);
    }

    @Override
    public Order queryOrder(String orderName) {
        return orderDao.findById(orderName);
    }
}
