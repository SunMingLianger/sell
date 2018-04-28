package com.sml.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Author is sunmingliang, Email sunmlcn@163.com,Date on 2018-04-17 14:56.
 * PS: Not easy to write code, please indicate.
 */
@Entity
@DynamicUpdate//动态更新
@Data
public class ProductCategory
{
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType)
    {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @SuppressWarnings("all")
    public ProductCategory()
    {
        super();
    }
}
