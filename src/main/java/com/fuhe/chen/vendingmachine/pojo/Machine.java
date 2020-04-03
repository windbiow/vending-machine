package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 机器
 */
@Data
public class Machine {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 机器所在位置
     */
    private String place;

    /**
     * 机器状态
     */
    private Integer machineStatus;

    /**
     * 库存状态
     */
    private Integer stock;
}
