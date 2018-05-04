package com.sml.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sml.enums.ProductStatus;
import com.sml.util.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 神迷的亮
 * 2018-04-18 09:27
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo implements Serializable
{
    private static final long serialVersionUID = 95184202412975155L;

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
    private Integer productStatus = ProductStatus.UP.getCode();

    //lei mu bian  hao
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatus getProductStatus()
    {
        return EnumUtil.getByCode(productStatus, ProductStatus.class);
    }
}
