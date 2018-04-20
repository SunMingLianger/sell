package com.sml.service.impl;

import com.sml.enums.ProductStatus;
import com.sml.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest
{

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void testFindUpAll() throws Exception
    {
        List<ProductInfo> upAll = productService.findUpAll();
        System.out.println(upAll);
        Assert.assertEquals(1, upAll.size());
    }

    @Test
    public void testFindAll() throws Exception
    {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> all = productService.findAll(request);

        System.out.println(all.getTotalElements());
    }

    @Test
    public void testFindOne() throws Exception
    {
        ProductInfo one = productService.findOne("123456");

        Assert.assertNotNull(one);
    }

    @Test
    public void testSave() throws Exception
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("iphone8 plus");
        productInfo.setProductDescription("乔布斯 你值得拥有！");
        productInfo.setProductPrice(BigDecimal.valueOf(5388.0));
        productInfo.setCategoryType(0);
        productInfo.setProductIcon("http://SUNMINGLIANG.COM");
        productInfo.setProductStock(10000);
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());
        ProductInfo save = productService.save(productInfo);

        Assert.assertNotNull(save);
    }

}   
  