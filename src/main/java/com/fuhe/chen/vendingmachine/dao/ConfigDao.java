package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Config;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 平台设置DAO
 */
@Mapper
public interface ConfigDao {

    /**
     * 新增平台设置
     * @param config
     */
    void addConfig(Config config);

    /**
     * 删除设置
     * @param id
     */
    void delete(int id);

    /**
     * 修改设置
     * @param config
     */
    void update(Config config);

    /**
     * 查询所有设置
     * @return
     */
    List<Config> findAll();
}
