package com.sml.util;

/**
 * 计算工具类
 * Created by 神迷的亮
 * 2018-04-24 13:11
 */
public class MathUtil
{
    /**
     * 判断支付金额是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equal(Double d1, Double d2)
    {
        double abs = Math.abs(d1 - d2);

        if (abs < 0.01)
        {
            return true;
        }
        return false;
    }
}
