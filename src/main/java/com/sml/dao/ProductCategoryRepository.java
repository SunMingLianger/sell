package com.sml.dao;

import com.sml.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author is sunmingliang, Email sunmlcn@163.com,Date on 2018-04-17 15:01.
 * PS: Not easy to write code, please indicate.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>
{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory findByCategoryNameIn(String categoryName);
}
