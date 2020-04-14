package com.fuhe.chen.vendingmachine.service;

import com.fuhe.chen.vendingmachine.dto.cond.OrderCond;
import com.fuhe.chen.vendingmachine.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 订单服务层
 */
public interface IOrderService {


    /**
     * 添加订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 查询所有订单
     * @return
     */
    PageInfo<Order> findAll(int pageNum, int pageSize);

    /**
     * 根据条件查询
     * @param cond 条件
     * @param pageNum  页数
     * @param size   每页条数
     * @return
     */
    PageInfo<Order> findByCondition(OrderCond cond, int pageNum, int size);

    /**
     * 根据订单主键查询
     * @param id
     * @return
     */
    Order findById(int id);

    /**
     * 修改订单状态,添加订单支付时间,订单支付人信息,支付方式,支付交易号
     * 修改在售商品信息,商品库存减量
     * 添加已售商品信息
     * @param out_trade_no 商户订单号
     * @param trade_no      支付宝交易号
     * @param payDate  支付时间
     * @param buyer_id      购买者id
     */
    void orderToSuccess(String out_trade_no, String trade_no, String payDate, String buyer_id) throws Exception;

    /**
     * 删除数组中订单id所对应的订单
     * @param orders
     */
    void delAll(List<String> orders);


    /**
     * 删除对应id的订单
     * @param orderId
     */
    void delete(String orderId);

    /**
     * 查询订单详情
     * @param orderName
     * @return
     */
    Order queryOrder(String orderName);
}
