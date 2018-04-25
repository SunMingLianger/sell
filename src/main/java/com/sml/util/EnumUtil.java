package com.sml.util;

import com.sml.enums.CodeEnum;

/**
 * Created by 神迷的亮
 * 2018-04-24 17:40
 */
public class EnumUtil
{

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass)
    {
        for (T each : enumClass.getEnumConstants())
        {
            if (code.equals(each.getCode()))
            {
                return each;
            }
        }
        return null;
    }
}
