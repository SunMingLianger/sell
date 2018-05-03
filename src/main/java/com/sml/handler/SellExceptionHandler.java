package com.sml.handler;

import com.sml.exception.ResponseBankException;
import com.sml.exception.SellAuthorizeException;
import com.sml.exception.SellException;
import com.sml.util.ResultVoUtil;
import com.sml.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截异常类
 * Created by 神迷的亮
 * 2018-05-03 13:45
 */
@ControllerAdvice
public class SellExceptionHandler
{
    //拦截登陆异常
    @ExceptionHandler(SellAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeExpection()
    {
        return new ModelAndView("redirect:http://localhost:8080/sell/seller/order/list");
    }

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e)
    {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException()
    {
    }
}
