package com.sml.enums;

import lombok.Getter;

/**
 * Created by 神迷的亮
 * 2018-04-19 09:18
 */
@Getter
public enum ResultEnum
{
    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_NULL(11, "库存不足"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态错误"),

    ORDER_UPDATE_FAIL(15, "更新订单状态失败"),

    ORDER_DETAIL_EMPTY(16, "订单中无商品详情"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态错误"),

    CART_EMPTY(18, "购物车为空"),;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}
