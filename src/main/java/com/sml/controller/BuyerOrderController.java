package com.sml.controller;

import com.sml.converter.OrderForm2OrderDTOConverter;
import com.sml.dto.OrderDTO;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.form.OrderForm;
import com.sml.service.OrderService;
import com.sml.util.ResultVoUtil;
import com.sml.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 买家订单控制层
 * Created by 神迷的亮
 * 2018-04-20 15:10
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVo<Map<String, String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors())
        {
            log.error("[创建订单] 参数不正确，orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
        {
            log.error("[创建订单] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createorder = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createorder.getOrderId());

        return ResultVoUtil.success(map);
    }

}
