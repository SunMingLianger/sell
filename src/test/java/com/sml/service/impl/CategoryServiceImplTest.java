package com.sml.service.impl;

import com.sml.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest
{
    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void testFindByCateGoryType() throws Exception
    {
        List<Integer> list = Arrays.asList(1, 0, 10);
        List<ProductCategory> byCateGoryType = service.findByCateGoryType(list);
        System.out.println(byCateGoryType);
    }

    @Test
    public void testFindByCategoryName() throws Exception
    {
        ProductCategory zdd = service.findByCategoryName("zdd");
        System.out.println(zdd);
    }

    @Test
    public void testFindAll() throws Exception
    {
        List<ProductCategory> all = service.findAll();
        log.info("开始查询所有商品类目！！！");
        System.out.println(all);
    }

    @Test
    public void testFindOne() throws Exception
    {

        ProductCategory one = service.findOne(4);

        if (one == null)
        {
            System.out.println("sunmingliang");
            throw new RuntimeException("there has an error!");

        }

    }

    @Test
    public void testSaveCategory() throws Exception
    {
        ProductCategory productCategory = new ProductCategory("孙明亮最爱", 1);

        ProductCategory productCategory1 = service.saveCategory(productCategory);

        log.error("save category!");
        System.out.println(productCategory1);
    }

}   
  