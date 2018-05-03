package com.sml.repository;

import com.sml.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 神迷的亮
 * 2018-04-18 16:26
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>
{
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
