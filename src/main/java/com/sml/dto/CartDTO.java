package com.sml.dto;

import lombok.Data;

/**
 * 购物车
 * Created by 神迷的亮
 * 2018-04-19 10:15
 */
@Data
public class CartDTO
{
    //商品ID
    private String productId;

    //shu liang
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity)
    {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO()
    {
        super();
    }
}
