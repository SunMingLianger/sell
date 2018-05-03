package com.sml.repository;

import com.sml.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 神迷的亮
 * 2018-05-02 15:57
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String>
{

    SellerInfo findByOpenid(String openId);
}
