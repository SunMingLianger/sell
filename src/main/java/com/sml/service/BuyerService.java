package com.sml.service;

import com.sml.dto.OrderDTO;

/**
 * 买家
 * Created by 神迷的亮
 * 2018-04-23 10:07
 */
public interface BuyerService
{

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
