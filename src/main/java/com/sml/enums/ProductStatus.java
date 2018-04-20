package com.sml.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by 神迷的亮
 * 2018-04-18 13:13
 */
@Getter
public enum ProductStatus
{
    UP(0, "在售"), DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatus(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
