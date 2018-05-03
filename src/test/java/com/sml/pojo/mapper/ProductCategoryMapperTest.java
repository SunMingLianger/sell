package com.sml.pojo.mapper;

import com.sml.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 神迷的亮
 * 2018-05-03 15:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest
{

    //idea对mapper文件注入都会报这种错，不用理会；
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "孙明亮最爱");
        map.put("category_Type", 8);

        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType()
    {
        ProductCategory productCategory = mapper.findByCategoryType(8);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByName()
    {
        List<ProductCategory> productCategory = mapper.findCateByName("孙明亮最爱");
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateBycategoryType()
    {
        int i = mapper.updateByCategoryType("孙明亮最不爱", 0);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void updateByOBj()
    {
        ProductCategory productCategory = new ProductCategory("孙明亮", 0);
        int i = mapper.updateByCategory(productCategory);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void delete()
    {
        int i = mapper.deleteByCategoryType(23);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testFindCaByType()
    {
        ProductCategory productCategory = mapper.selectByCategoryType(0);
        Assert.assertNotNull(productCategory);
    }
}