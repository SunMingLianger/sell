package com.sml.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sml.dto.OrderDTO;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.form.OrderForm;
import com.sml.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 神迷的亮
 * 2018-04-20 15:50
 */
@Slf4j
public class OrderForm2OrderDTOConverter
{
    public static OrderDTO convert(OrderForm orderForm)
    {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try
        {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>()
            {
            }.getType());

        }
        catch (Exception e)
        {
            log.error("对象转换错误 ，String  = {}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
