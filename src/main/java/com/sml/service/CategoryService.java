package com.sml.service;

import com.sml.pojo.ProductCategory;

import java.util.List;

/**
 * Author is sunmingliang, Email sunmlcn@163.com,Date on 2018-04-17 17:33.
 * PS: Not easy to write code, please indicate.
 */
public interface CategoryService
{
    List<ProductCategory> findByCateGoryType(List<Integer> list);

    ProductCategory findByCategoryName(String categoryName);

    List<ProductCategory> findAll();

    ProductCategory findOne(Integer categoryId);

    ProductCategory saveCategory(ProductCategory category);

}
