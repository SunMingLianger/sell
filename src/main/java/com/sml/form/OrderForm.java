package com.sml.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 前台页面传递过来的表单传输实体
 * Created by 神迷的亮
 * 2018-04-20 15:32
 */
@Data
public class OrderForm
{
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
