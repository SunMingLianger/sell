package com.sml.service.impl;

import com.sml.dto.OrderDTO;
import com.sml.enums.OrderStatusEnum;
import com.sml.enums.PayStatusEnum;
import com.sml.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest
{

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testCreateOrder() throws Exception
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("sun");
        orderDTO.setBuyerAddress("china xi an");
        orderDTO.setBuyerOpenid("283076358");
        orderDTO.setBuyerPhone("18710974321");
        orderDTO.setOrderAmount(new BigDecimal(1999.98));
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(90);
        orderDetail.setProductPrice(new BigDecimal(90.0));

        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);

        log.info("创建订单 result ={}", result);

    }

    @Test
    public void testFindOne() throws Exception
    {
        OrderDTO one = orderService.findOne("123456");

        log.info("[查询单个订单] result = {}", one);
    }

    @Test
    public void testFindOrderList() throws Exception
    {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderList = orderService.findOrderList("283076358", request);
        Assert.assertNotEquals(0, orderList.getTotalElements());
    }

    @Test
    public void testCancelOrder() throws Exception
    {

        OrderDTO orderDTO = orderService.findOne("123456");

        OrderDTO cancelOrder = orderService.cancelOrder(orderDTO);

        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancelOrder.getOrderStatus());

    }

    @Test
    public void testFinishOrder() throws Exception
    {
        OrderDTO orderDTO = orderService.findOne("123456");

        OrderDTO finishOrder = orderService.finishOrder(orderDTO);

        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), finishOrder.getOrderStatus());

    }

    @Test
    public void testPaidOrder() throws Exception
    {
        OrderDTO orderDTO = orderService.findOne("123456");

        OrderDTO paidOrder = orderService.paidOrder(orderDTO);

        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), paidOrder.getPayStatus());
    }

}   
  