package com.sml.vo;

import lombok.Data;

/**
 * Created by 神迷的亮
 * 2018-04-18 13:52
 */
@Data
public class ResultVo<T>
{
    /**错误码。 */
    private Integer code;

    //TISHIXINXI
    private String msg;

    private T data;
}
