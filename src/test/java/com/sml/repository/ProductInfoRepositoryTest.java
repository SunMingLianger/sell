package com.sml.repository;

import com.sml.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 神秘的亮
 * 2018-04-18 09:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest
{
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void testSave()
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("小米手机7");
        productInfo.setProductDescription("性能彪悍，值得拥有！");
        productInfo.setProductPrice(BigDecimal.valueOf(2899.32));
        productInfo.setCategoryType(10);
        productInfo.setProductIcon("http://sss");
        productInfo.setProductStock(9999);
        ProductInfo save = repository.save(productInfo);

        Assert.assertNotNull(save);
    }

    @Test
    public void testUpdate()
    {
        ProductInfo one = repository.findOne("123456");

        one.setProductStatus(0);
        repository.save(one);
    }

    @Test
    public void findByProductStatus()
    {
        List<ProductInfo> byProductStatus = repository.findByProductStatus(0);
        System.out.println(byProductStatus);
        Assert.assertNotEquals(0, byProductStatus.size());
    }
}