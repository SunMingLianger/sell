package com.sml.controller;

import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.pojo.ProductInfo;
import com.sml.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 神迷的亮
 * 2018-04-27 15:33
 */
@Controller
@RequestMapping("/seller/product")
public class SellProductController
{
    @Autowired
    private ProductService productService;

    /**
     * 商品列表
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size, Map<String, Object> map)
    {
        PageRequest pageRequest = new PageRequest(page - 1, size);

        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     *
     * @param productId
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map)
    {
        try
        {
            productService.onSale(productId);
        }
        catch (SellException e)
        {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_ONSALE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map)
    {
        try
        {
            productService.offSale(productId);
        }
        catch (SellException e)
        {
            map.put("url", "/sell/seller/product/list");
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_OFFSALE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
