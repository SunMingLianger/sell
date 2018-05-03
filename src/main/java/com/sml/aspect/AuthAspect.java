//package com.sml.aspect;
//
//import com.sml.constant.RedisConstant;
//import com.sml.exception.SellAuthorizeException;
//import com.sml.util.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 登陆校验切面类
// * Created by 神迷的亮
// * 2018-05-03 13:25
// */
//@Aspect
//@Component
//@Slf4j
//public class AuthAspect
//{
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    //切点
//    @Pointcut("execution(public * com.sml.controller.Sell*.*(..))")
//    public void verify()
//    {
//    }
//
//    @Before("verify()")//在切点之前进行
//    public void doVerify()
//    {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        HttpServletRequest request = attributes.getRequest();
//
//        Cookie cookie = CookieUtil.getCookie(request, "token");
//        if (cookie == null)
//        {
//            log.warn("[登陆校验] Cookie中查不到token");
//            throw new SellAuthorizeException();
//        }
//
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//
//        if (StringUtils.isEmpty(tokenValue))
//        {
//            log.warn("[登陆校验] Redis中查不到token");
//            throw new SellAuthorizeException();
//        }
//    }
//
//}
