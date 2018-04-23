package com.sml.controller;

import com.sml.converter.OrderForm2OrderDTOConverter;
import com.sml.dto.OrderDTO;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.form.OrderForm;
import com.sml.service.BuyerService;
import com.sml.service.OrderService;
import com.sml.util.ResultVoUtil;
import com.sml.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private BuyerService buyerService;

    //创建订单
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

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> listOrder(@RequestParam("openid") String openid, @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        if (StringUtils.isEmpty(openid))
        {
            log.error("[查询订单列表] openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);

        Page<OrderDTO> orderList = orderService.findOrderList(openid, request);

        return ResultVoUtil.success(orderList.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDTO> listOrderDetail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId)
    {
        // TODO: 18/4/23 不安全，openID没用到
       /* OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openid))
        {

        }*/

        //改造
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultVoUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId)
    {

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);

        return ResultVoUtil.success();
    }

}
