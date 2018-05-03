package com.sml.service.impl;

import com.sml.repository.SellerInfoRepository;
import com.sml.pojo.SellerInfo;
import com.sml.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 神迷的亮
 * 2018-05-02 16:31
 */
@Service
@Transactional
public class SellerServiceImpl implements SellerService
{

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId)
    {
        return sellerInfoRepository.findByOpenid(openId);
    }
}
