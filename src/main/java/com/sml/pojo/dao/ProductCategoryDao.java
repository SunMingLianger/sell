package com.sml.pojo.dao;

import com.sml.pojo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by 神迷的亮
 * 2018-05-03 16:56
 */
public class ProductCategoryDao
{
    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByMap(Map<String, Object> map)
    {
        return mapper.insertByMap(map);
    }
}
