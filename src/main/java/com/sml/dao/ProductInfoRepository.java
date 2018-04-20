package com.sml.dao;

import com.sml.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 神秘的亮
 * 2018-04-18 09:38
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>
{
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
