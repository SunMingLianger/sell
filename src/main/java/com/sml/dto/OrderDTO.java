package com.sml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sml.enums.OrderStatusEnum;
import com.sml.enums.PayStatusEnum;
import com.sml.pojo.OrderDetail;
import com.sml.util.EnumUtil;
import com.sml.util.serializer.Date2LongSerializer;
import com.sml.util.serializer.Date2StringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 对ORDERMASTER的封装。能封装时就用封装的，免得一些额外的参数信息被不法份子截获，获取你的情报
 * Created by 神迷的亮
 * 2018-04-18 17:23
 */
@Data
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
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
    @JsonSerialize(using = Date2StringSerializer.class)
    private Date createTime;

    //订单更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    //订单详情
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum()
    {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum()
    {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
