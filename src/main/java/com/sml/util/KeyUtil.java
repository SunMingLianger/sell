package com.sml.util;

import java.util.Random;

/**
 * Created by 神迷的亮
 * 2018-04-19 09:37
 */
public class KeyUtil
{
    /**
     * 生成唯一的主健
     * 格式：时间＋随机数
     *
     * @return
     */
    public static synchronized String getUniqueKey()
    {
        Random random = new Random();

        Integer a = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(a);

    }
}
