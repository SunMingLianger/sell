package com.sml.service.impl;

import com.sml.dao.ProductCategoryRepository;
import com.sml.pojo.ProductCategory;
import com.sml.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目服务
 * Author is sunmingliang, Email sunmlcn@163.com,Date on 2018-04-17 17:38.
 * PS: Not easy to write code, please indicate.
 */
@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> findByCateGoryType(List<Integer> list)
    {
        return repository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory findByCategoryName(String categoryName)
    {
        return repository.findByCategoryNameIn(categoryName);
    }

    @Override
    public List<ProductCategory> findAll()
    {
        return repository.findAll();
    }

    @Override
    public ProductCategory findOne(Integer categoryId)
    {
        return repository.findOne(categoryId);
    }

    @Override
    public ProductCategory saveCategory(ProductCategory category)
    {
        return repository.save(category);
    }
}
