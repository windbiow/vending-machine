package com.fuhe.chen.vendingmachine.service;

import java.util.Map;

/**
 * 数据统计服务层
 */
public interface IStatisticService {

    /**
     * 平台总体数据
     * @return
     */
    Map<String,String> globalData();
}
