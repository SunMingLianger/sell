package com.sml.controller;

import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.form.CategoryForm;
import com.sml.pojo.ProductCategory;
import com.sml.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 * Created by 神迷的亮
 * 2018-04-28 14:35
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object> map)
    {
        if (categoryId != null)
        {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map)
    {
        List<ProductCategory> productCategoryList = categoryService.findAll();

        map.put("categoryList", productCategoryList);

        return new ModelAndView("category/list", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult, Map<String, Object> map)
    {

        if (bindingResult.hasErrors())
        {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        try
        {
            if (form.getCategoryId() != null && form.getCategoryId() > 0)
            {
                productCategory = categoryService.findOne(form.getCategoryId());
            }

            BeanUtils.copyProperties(form, productCategory);
            categoryService.saveCategory(productCategory);
        }
        catch (SellException e)
        {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("common/error", map);

        }
        map.put("msg", ResultEnum.CATEGORY_SAVE_SUCCESS.getMsg());
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
