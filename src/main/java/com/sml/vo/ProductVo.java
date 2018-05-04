package com.sml.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 * Created by 神迷的亮
 * 2018-04-18 14:04
 */
@Data
public class ProductVo implements Serializable
{
    private static final long serialVersionUID = 8954313750636982747L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVos;
}
