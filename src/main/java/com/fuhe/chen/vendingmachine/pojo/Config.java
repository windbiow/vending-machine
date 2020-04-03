package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

/**
 * 平台配置
 */
@Data
public class Config {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 参数名
     */
    private String name;

    /**
     * 参数值
     */
    private String value;
}
