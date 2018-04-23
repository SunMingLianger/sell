package com.sml.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by 神迷的亮
 * 2018-04-20 17:59
 */
public class Date2LongSerializer extends JsonSerializer<Date>
{

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException
    {
        long l = date.getTime() / 1000;

        jsonGenerator.writeNumber(l);
    }
}
