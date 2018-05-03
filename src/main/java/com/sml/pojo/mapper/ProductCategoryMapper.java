package com.sml.pojo.mapper;

import com.sml.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 神迷的亮
 * 2018-05-03 15:33
 */
public interface ProductCategoryMapper
{
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_Type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Select("select * from product_category where category_type = #{cateType,jdbcType=INTEGER}")
    @Results({@Result(column = "category_type", property = "categoryType"), @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName")})
    ProductCategory findByCategoryType(Integer cateType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({@Result(column = "category_type", property = "categoryType"), @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName")})
    List<ProductCategory> findCateByName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategory(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryType(Integer categoryType);
}
