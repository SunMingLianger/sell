package com.sml.service.impl;

import com.sml.dao.ProductInfoRepository;
import com.sml.dto.CartDTO;
import com.sml.enums.ProductStatus;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.pojo.ProductInfo;
import com.sml.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务
 * Created by 神秘的亮
 * 2018-04-18 13:08
 */
@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public List<ProductInfo> findUpAll()
    {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo findOne(String productId)
    {
        return repository.findOne(productId);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo)
    {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList)
    { //库存
        Integer productStock;
        for (CartDTO cartDTO : cartDTOList)
        {
            ProductInfo one = repository.findOne(cartDTO.getProductId());
            if (one == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productStock = one.getProductStock() + cartDTO.getProductQuantity();

            one.setProductStock(productStock);

            repository.save(one);
        }

    }

    @Override
    @Transactional
    // TODO: 18/4/20 假如同时有两个人扣库存，就会存在超卖问题，就是商品卖的比实际多了，以后会在后面对代码优化；
    public void decreaseStock(List<CartDTO> cartDTOList)
    {
        //库存
        Integer productStock;
        for (CartDTO cartDTO : cartDTOList)
        {
            ProductInfo one = repository.findOne(cartDTO.getProductId());

            if (one == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productStock = one.getProductStock() - cartDTO.getProductQuantity();

            if (productStock < 0)
            {
                throw new SellException(ResultEnum.PRODUCT_STOCK_NULL);
            }

            one.setProductStock(productStock);

            repository.save(one);
        }
    }
}
