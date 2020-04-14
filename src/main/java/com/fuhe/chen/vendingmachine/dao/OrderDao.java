package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单DAO
 */
@Mapper
public interface OrderDao {

    /**
     * 添加订单
     * @param order
     */
    void addOrder(Order order);

    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAll();

    /**
     * 根据条件查询
     * @param orderStatus 订单状态
     * @param payMethod  支付方式
     * @param place   交易地点
     * @return
     */
    List<Order> findByCondition(@Param("orderStatus") Integer orderStatus,
                                @Param("payMethod")  Integer payMethod,
                                @Param("place")  String place,
                                @Param("start")  Long start,
                                @Param("end")  Long end,
                                @Param("trade_no")  String trade_no
    );

    /**
     * 根据订单编号查询
     * @param orderName
     * @return
     */
    Order findById(@Param("orderName") String orderName);

    /**
     * 修改订单信息
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 查询销售总金额
     * @return
     */
    Double totalSalesAmount();

    /**
     * 查询销售总单数
     * @return
     */
    Integer totalSalesOrders();

    /**
     * 查询订单所属的机器id
     * @param orderName
     * @return
     */
    Integer findMachineId(String orderName);

    /**
     * 批量删除
     * @param orders
     */
    void delAll(List<String> orders);

    /**
     * 删除
     * @param orderId
     */
    void delete(@Param("orderId") String orderId);
}
