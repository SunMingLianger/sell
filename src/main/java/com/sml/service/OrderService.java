package com.sml.service;

import com.sml.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 神迷的亮
 * 2018-04-18 17:16
 */
public interface OrderService
{
    //     创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //    查询单个订单
    OrderDTO findOne(String orderId);

    //    查询单个用户订单列表
    Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable);

    //查询所有订单列表
    Page<OrderDTO> findList(Pageable pageable);

    //    取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);

    //    完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);

    //    支付订单
    OrderDTO paidOrder(OrderDTO orderDTO);

}
