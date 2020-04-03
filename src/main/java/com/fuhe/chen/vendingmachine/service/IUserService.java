package com.fuhe.chen.vendingmachine.service;

import com.fuhe.chen.vendingmachine.pojo.User;

import java.util.List;

/**
 * 用户服务层
 */
public interface IUserService {

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
