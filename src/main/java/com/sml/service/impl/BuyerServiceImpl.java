package com.sml.service.impl;

import com.sml.dto.OrderDTO;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.service.BuyerService;
import com.sml.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 神迷的亮
 * 2018-04-23 10:09
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService
{

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId)
    {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId)
    {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        if (orderDTO == null)
        {
            log.error("[取消订单] 查不到该订单，orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId)
    {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null)
        {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid))
        //== equals的区别：前者比较内存地址，后者比较字符串内容  ，string 一般 equals
        {
            log.error("openid 不一致，openid ={}", orderDTO.getBuyerOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
