package com.sml.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json格式化工具类，使用Google的GSON
 * Created by 神迷的亮
 * 2018-04-24 10:16
 */
public class JsonUtil
{
    public static String toJson(Object o)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        return gson.toJson(o);
    }
}
