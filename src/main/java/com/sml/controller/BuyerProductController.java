package com.sml.controller;

import com.sml.pojo.ProductCategory;
import com.sml.pojo.ProductInfo;
import com.sml.service.CategoryService;
import com.sml.service.ProductService;
import com.sml.util.ResultVoUtil;
import com.sml.vo.ProductInfoVo;
import com.sml.vo.ProductVo;
import com.sml.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品控制层
 * Created by 神迷的亮
 * 2018-04-18 13:49
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "#sellerId", condition = "#sellerId.length()>3", unless = "#result.getCode() != 0")
    public ResultVo list(@RequestParam("sellerId") String sellerId)
    {
        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> collect = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> byCateGoryType = categoryService.findByCateGoryType(collect);

        ArrayList<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : byCateGoryType)
        {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            ArrayList<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList)
            {
                if (productCategory.getCategoryType() == productInfo.getCategoryType())
                {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                  /*  productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductId(productInfo.getProductId());*/

                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);

                }
            }
            productVo.setProductInfoVos(productInfoVoList);
            productVoList.add(productVo);

        }

        return ResultVoUtil.success(productVoList);
    }
}
