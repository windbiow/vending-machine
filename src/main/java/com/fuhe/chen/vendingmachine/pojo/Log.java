package com.fuhe.chen.vendingmachine.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 */
@Data
public class Log {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户主键
     */
    private String userId;

    /**
     * 操作
     */
    private String operation;

    /**
     * 操作时间
     */
    private Long operateDate;
}
