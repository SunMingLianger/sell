package com.sml.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by 神迷的亮
 * 2018-05-14 17:20
 */
public class Date2StringSerializer extends JsonSerializer<Date>
{
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException
    {
        long l = date.getTime();

        jsonGenerator.writeString(String.valueOf(l));

    }
}
