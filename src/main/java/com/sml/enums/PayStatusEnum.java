package com.sml.enums;

import lombok.Getter;

/**
 * Created by 神迷的亮
 * 2018-04-18 16:09
 */
@Getter
public enum PayStatusEnum
{
    WAIT(0, "等待支付"), SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}