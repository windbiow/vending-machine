package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 日志DAO
 */
@Mapper
public interface LogDao {

    /**
     * 新增操作日志
     * @param log
     */
    void addLog(Log log);

    /**
     * 查询所有
     * @return
     */
    List<Log> findAll();

    /**
     * 根据条件查询
     * @param operation
     * @param startDate
     * @param endDate
     * @return
     */
    List<Log> findByCondition(String operation, Date startDate,Date endDate);

}
