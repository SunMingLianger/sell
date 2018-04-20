package com.sml.dao;

import com.sml.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 神迷的亮
 * 2018-04-18 16:59
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest
{

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId()
    {
        List<OrderDetail> details = repository.findByOrderId("123456");

        System.out.println(details);
    }

    @Test
    public void save()
    {
        OrderDetail detail = new OrderDetail();

        detail.setDetailId("124");
        detail.setOrderId("123457");
        detail.setProductIcon("http://localhost:sun");
        detail.setProductName("灯具 美壁纸");
        detail.setProductId("123457");
        detail.setProductQuantity(22);
        detail.setProductPrice(new BigDecimal(278));
        OrderDetail orderDetail = repository.save(detail);

        Assert.assertNotNull(orderDetail);
    }
}