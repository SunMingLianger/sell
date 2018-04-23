package com.sml.vo;

import lombok.Data;

/**
 * 后台处理后返回到前台的实体封装类
 * Created by 神迷的亮
 * 2018-04-18 13:52
 */
@Data
public class ResultVo<T>
{
    /**
     * 错误码。
     */
    private Integer code;

    //提示信息
    private String msg;

    //包装的对象
    private T data;
}
