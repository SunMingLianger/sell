package com.sml.converter;

import com.sml.dto.OrderDTO;
import com.sml.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换包装类
 * Created by 神迷的亮
 * 2018-04-20 09:53
 */
public class OrderMaster2OrderDTOConverter
{
    public static OrderDTO convert(OrderMaster orderMaster)
    {
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster, orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList)
    {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
