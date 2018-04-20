package com.sml.exception;

import com.sml.enums.ResultEnum;

/**
 * yichang lei
 * Created by 神迷的亮
 * 2018-04-19 09:17
 */
public class SellException extends RuntimeException
{
    private Integer code;

    public SellException(ResultEnum resultEnum)
    {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }
}
