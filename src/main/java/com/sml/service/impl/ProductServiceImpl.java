package com.sml.service.impl;

import com.sml.dto.CartDTO;
import com.sml.enums.ProductStatus;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.pojo.ProductInfo;
import com.sml.repository.ProductInfoRepository;
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
//@CacheConfig(cacheNames = "product")
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
    //    @Cacheable(key = "123")
    public ProductInfo findOne(String productId)
    {
        return repository.findOne(productId);
    }

    @Override
    //    @CachePut(key = "123")
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

    @Override
    public ProductInfo onSale(String productId)
    {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo == null)
        {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus() == ProductStatus.UP)
        {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatus.UP.getCode());

        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId)
    {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo == null)
        {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus() == ProductStatus.DOWN)
        {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());

        return repository.save(productInfo);
    }
}
