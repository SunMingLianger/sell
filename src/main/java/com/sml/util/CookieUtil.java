package com.sml.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * Created by 神迷的亮
 * 2018-05-03 09:22
 */
public class CookieUtil
{
    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxage
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxage)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxage);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equalsIgnoreCase(cookieName))
                {
                    return cookie;
                }
            }
        }
        return null;
    }
}
