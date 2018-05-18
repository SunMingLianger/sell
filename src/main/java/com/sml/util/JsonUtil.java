package com.sml.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

/**
 * json格式化工具类，使用Google的GSON
 * Created by 神迷的亮
 * 2018-04-24 10:16
 */
public class JsonUtil<T>
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object o)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        return gson.toJson(o);
    }

    /**
     * obj 转json
     *
     * @param o
     * @return
     */
    public static String toJson1(Object o)
    {
        try
        {
            objectMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
            return objectMapper.writeValueAsString(o);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     *
     * @param msg
     * @param tClass
     * @return
     */
    public static Object fromJson(String msg, Class tClass)
    {
        try
        {
            return objectMapper.readValue(msg, tClass);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
