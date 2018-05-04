package com.sml.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP地址
 * Created by 神迷的亮
 * 2018-05-04 16:49
 */
public class GetIpAddress
{
    public static String getIp(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (!ip.contains(","))
        {
            ip = ip;
        }
        else
        {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
