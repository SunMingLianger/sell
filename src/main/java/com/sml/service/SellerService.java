package com.sml.service;

import com.sml.pojo.SellerInfo;

/**
 * 卖家端service
 * Created by 神迷的亮
 * 2018-05-02 16:29
 */
public interface SellerService
{
    SellerInfo findSellerInfoByOpenId(String openId);

}
