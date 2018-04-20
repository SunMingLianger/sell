package com.sml.dao;

import com.sml.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by 神迷的亮
 * 2018-04-18 16:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest
{

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid()
    {
        PageRequest pageRequest = new PageRequest(0, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid("283076358", pageRequest);

        System.out.println(result);

        Assert.assertEquals(1, result.getSize());

    }

    @Test
    public void save()
    {
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setBuyerName("zdd");
        orderMaster.setBuyerAddress("shang  hai ");
        orderMaster.setBuyerPhone("123456789");
        orderMaster.setBuyerOpenid("29292992929");
        orderMaster.setOrderAmount(new BigDecimal(2222.12));
        orderMaster.setOrderId("123457");

        OrderMaster save = repository.save(orderMaster);

        Assert.assertNotNull(save);
    }
}