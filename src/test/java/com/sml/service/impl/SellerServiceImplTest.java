package com.sml.service.impl;

import com.sml.pojo.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 神迷的亮
 * 2018-05-02 16:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest
{

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenId()
    {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId("1234");

        Assert.assertEquals("1234", sellerInfo.getOpenid());
    }
}