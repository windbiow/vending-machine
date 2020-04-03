package com.fuhe.chen.vendingmachine.dao;

import com.fuhe.chen.vendingmachine.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类DAO
 */
@Mapper
public interface CategoryDao {

    /**
     * 新增分类
     * @param category
     */
    void addCategory(Category category);

    /**
     * 修改分类
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除分类
     * @param id
     */
    void deleteCategory(int id);

    /**
     * 查询所有
     * @return
     */
    List<Category> findAll();

    /**
     * 查询单个
     * @param id
     * @return
     */
    Category findOne(@Param("id") Integer id);
}
