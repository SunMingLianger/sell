package com.sml.service.impl;

import com.sml.converter.OrderMaster2OrderDTOConverter;
import com.sml.dao.OrderDetailRepository;
import com.sml.dao.OrderMasterRepository;
import com.sml.dto.CartDTO;
import com.sml.dto.OrderDTO;
import com.sml.enums.OrderStatusEnum;
import com.sml.enums.PayStatusEnum;
import com.sml.enums.ResultEnum;
import com.sml.exception.SellException;
import com.sml.pojo.OrderDetail;
import com.sml.pojo.OrderMaster;
import com.sml.pojo.ProductInfo;
import com.sml.service.OrderService;
import com.sml.service.ProductService;
import com.sml.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务
 * Created by 神迷的亮
 * 2018-04-18 17:29
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO)
    {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        String orderId = KeyUtil.getUniqueKey();
        //查询商品（数量，价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail detail : orderDetailList)
        {
            ProductInfo productInfo = productService.findOne(detail.getProductId());
            if (productInfo == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价＊bigdecimal使用乘法时使用multiply.使用加法时使用add；
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            detail.setDetailId(KeyUtil.getUniqueKey());
            detail.setOrderId(orderId);
            //spring 提供的copy属性的快捷方式
            BeanUtils.copyProperties(productInfo, detail);
            orderDetailRepository.save(detail);
        }

        //写入订单数据库（ordermaster，order的 tail）
        OrderMaster orderMaster = new OrderMaster();

        /** 使用beanutils时一定注意可能存在遗漏的属性覆盖。 */
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId)
    {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);

        if (orderMaster == null)
        {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);

        if (CollectionUtils.isEmpty(orderDetailList))
        {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable)
    {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO)
    {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("[取消订单] 订单状态不正确，orderId = {},orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null)
        {
            log.error("[取消订单] 更新失败，orderMaster = {}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
        {
            log.error("［取消订单］订单中无商品详情，orderDTO = {}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.increaseStock(cartDTOList);

        //如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode()))
        {
            // TODO: 18/4/20 退款尚未开发
        }
        return orderDTO;
    }

    /**
     * 完结订单
     *
     * @param orderDTO 订单对象
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO)
    {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("[完结订单] result = {}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());

        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDTO, orderMaster);

        //存入数据库
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if (updateResult == null)
        {
            log.error("[完结订单] 更新失败，orderMaster = {} ", orderMaster);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paidOrder(OrderDTO orderDTO)
    {
        //判断订单状态

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("[订单支付异常：] 支付订单异常：result = {}", orderDTO);

            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode()))
        {
            log.error("[订单支付异常：] 支付订单状态错误 error = {}", orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);

        if (orderMaster1 == null)
        {
            log.error("[订单支付异常：] errorPayStatus = {}", orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
