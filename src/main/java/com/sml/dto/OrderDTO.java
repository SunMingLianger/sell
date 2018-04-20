package com.sml.dto;

import com.sml.pojo.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 神迷的亮
 * 2018-04-18 17:23
 */
@Data
public class OrderDTO implements Serializable
{
    private static final long serialVersionUID = -4148563847910550747L;

    //订单ID
    private String orderId;

    //买家名字
    private String buyerName;

    //买家手机号
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信 openID
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态 默认为0新下单
    private Integer orderStatus;

    //支付状态 默认为0未支付
    private Integer payStatus;

    // 订单创建时间
    private Date createTime;

    //订单更新时间
    private Date updateTime;

    //订单详情
    private List<OrderDetail> orderDetailList;

}
