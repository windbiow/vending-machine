package com.fuhe.chen.vendingmachine.common.redis;

/**
 * Redis缓存名称
 */
public interface RedisConstant {

    interface ShoppingCart {
        static final String SHOPPINGCART = "shoppingCart";
    }

    interface GlobalData {
        static final String GLOBALDATA = "globalData";
        static final String TOTALSALESAMOUNT = "totalSalesAmount";
        static final String TOTALSALESORDERS = "totalSalesOrders";
        static final String TOTALSALESCOMMODITIES = "totalSalesCommodities";
    }

}
