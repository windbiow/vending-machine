package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户DAO
 */
@Mapper
public interface UserDao {

    /**
     * 新增用户
     */
    void addUser(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 查询单个用户
     * @param id
     * @return
     */
    User findOne(String id);
}
