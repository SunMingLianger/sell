package com.sml.repository;

import com.sml.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Author is sunmingliang, Email sunmlcn@163.com,Date on 2018-04-17 15:23.
 * PS: Not easy to write code, please indicate.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest
{

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void test1()
    {
        ProductCategory one = repository.findOne(1);
        System.out.println(one);
    }

    @Test
    public void test2()
    {
        ProductCategory one = repository.findOne(2);

        one.setCategoryType(10);

        repository.save(one);

    }

    @Test
    public void test3()
    {
        List<Integer> list = Arrays.asList(0, 10, 2);

        List<ProductCategory> categories = repository.findByCategoryTypeIn(list);

        System.out.println(categories);

    }

}