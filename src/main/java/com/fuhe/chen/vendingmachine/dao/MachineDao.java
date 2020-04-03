package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机器DAO
 */
@Mapper
public interface MachineDao{

    /**
     * 新增机器
     * @param machine
     */
    void addMachine(Machine machine);

    /**
     * 修改机器信息
     * @param machine
     */
    void updateMachine(Machine machine);

    /**
     * 查询所有机器
     * @return
     */
    List<Machine> findAll();

    /**
     * 根据机器的运行状态和库存状态查询机器
     * @param machineStatus
     * @param stock
     * @return
     */
    List<Machine> findByStatus(int machineStatus,int stock);

    /**
     * 根据机器所在地模糊查询
     * @param place
     * @return
     */
    List<Machine> findByPlace(String place);

    /**
     * 根据机器id查询
     * @param machineId 机器id
     * @return
     */
    Machine findOne(@Param("id") Integer machineId);

}
