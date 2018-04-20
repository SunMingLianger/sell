package com.sml.controller;

import com.sml.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 神迷的亮
 * 2018-04-20 15:10
 */
@RestController
@RequestMapping("/order")
public class BuyerOrderController
{
    @Autowired
    private OrderService orderService;

}
