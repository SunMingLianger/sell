package com.sml.form;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Created by 神迷的亮
 * 2018-04-28 15:03
 */
@Data
@DynamicUpdate
public class CategoryForm
{

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

}
