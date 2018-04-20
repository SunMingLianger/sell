package com.sml.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by 神迷的亮
 * 2018-04-18 09:27
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo
{
    @Id
    private String productId;

    /**
     * 商品名字
     */
    private String productName;

    /**
     * dan jia.
     */
    private BigDecimal productPrice;

    //ku cun
    private Integer productStock;

    //miao shu
    private String productDescription;

    //shang pin tu pian xiao tu
    private String productIcon;

    /**
     * zhuang tai 0 zhengchang 1 xia jia.
     */
    private Integer productStatus;

    //lei mu bian  hao
    private Integer categoryType;
}
