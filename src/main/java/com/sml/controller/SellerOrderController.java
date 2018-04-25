package com.sml.controller;

import com.sml.dto.OrderDTO;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 神迷的亮
 * 2018-04-24 16:27
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController
{

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     *
     * @param page 第几页，从1页开始
     * @param size 一页有多少条数据
     * @return 逻辑视图
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size, Map<String, Object> map)
    {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);

        map.put("orderDTOPage", orderDTOPage);
        map.put("size", size);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);

    }

    /**
     * 取消订单
     *
     * @param orderId 订单ID
     * @param map 传输map
     * @return 逻辑视图
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderid") String orderId, Map<String, Object> map)
    {
        try
        {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancelOrder(orderDTO);
        }
        catch (SellException e)
        {
            log.error("[卖家端取消订单] there had an exception");
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("/common/success");
    }
}

