package com.sml.repository;

import com.sml.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 神迷的亮
 * 2018-04-18 16:31
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>
{
    List<OrderDetail> findByOrderId(String orderId);

}
