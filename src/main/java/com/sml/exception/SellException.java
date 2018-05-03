package com.sml.exception;

import com.sml.enums.ResultEnum;
import lombok.Getter;

/**
 * yichang lei
 * Created by 神迷的亮
 * 2018-04-19 09:17
 */
@Getter
public class SellException extends RuntimeException
{
    private Integer code;

    public SellException(ResultEnum resultEnum)
    {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage)
    {
        super(defaultMessage);
        this.code = code;
    }
}
