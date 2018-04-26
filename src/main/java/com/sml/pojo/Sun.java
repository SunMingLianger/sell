package com.sml.pojo;

import lombok.Data;

/**
 * Builder模式，还没见过的一种构造模式
 * Created by 神迷的亮
 * 2018-04-25 11:28
 */
@Data
public class Sun
{
    private String id;

    private String name;

    public Sun(Builder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder
    {
        private String name;

        private String id;

        public Builder()
        {
        }

        public Builder setId(final String id)
        {
            this.id = id;
            return this;
        }

        public Builder setName(final String name)
        {
            this.name = name;

            return this;
        }

        public Sun build()
        {
            return new Sun(this);
        }
    }

    public static void main(String[] args)
    {
        Sun sun = new Builder().setId("12").setName("name").build();

        System.out.println(sun);
    }
}
