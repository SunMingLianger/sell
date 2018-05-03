package com.sml.repository;

import com.sml.pojo.SellerInfo;
import com.sml.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 神迷的亮
 * 2018-05-02 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //在配置测试的时候，直接回滚，不提交事务
public class SellerInfoRepositoryTest
{
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save()
    {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("123");
        sellerInfo.setId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("sun");
        sellerInfo.setPassword("2333");

        SellerInfo save = sellerInfoRepository.save(sellerInfo);

        Assert.assertNotNull(save);
    }

    @Test
    public void findByopenId()
    {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("1234");

        Assert.assertNotNull(sellerInfo);
    }
}