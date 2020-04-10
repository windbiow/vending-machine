package com.fuhe.chen.vendingmachine.constant;

/**
 * Created by Donghua.Chen on 2018/4/29.
 */
public interface ErrorConstant {

    interface Common {
        static final String PARAM_IS_EMPTY = "参数为空";
        static final String INVALID_PARAM = "无效的参数";
        static final String CAN_NOT_FIND_PARAM_TO_CONTIUNE = "找不到参数继续运行";
    }

    interface ShoppingCart {
        static final String SHOPPINGCART_IS_NULL = "购物车中没有商品!请先将商品添加购物车";
    }

    interface Att {
        static final String ADD_NEW_ATT_FAIL = "添加附件信息失败";
        static final String UPDATE_ATT_FAIL =  "更新附件信息失败";
        static final String DELETE_ATT_FAIL = "删除附件信息失败";
        static final String UPLOAD_FILE_FAIL = "上传附件失败";
    }
}
