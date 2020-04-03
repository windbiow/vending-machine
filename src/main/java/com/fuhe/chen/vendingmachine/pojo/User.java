package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 用户
 */
@Data
public class User {

    /**
     * 主键
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 支付id
     */
    private String payId;

    /**
     * 账户状态
     */
    private Integer status;

    /**
     * 账户权限
     */
    private String role;

    /**
     * 注册日期
     */
    private Long registerDate;

    /**
     * 最后一次修改时间
     */
    private Long updateDate;
}
