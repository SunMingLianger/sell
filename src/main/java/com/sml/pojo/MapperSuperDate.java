package com.sml.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by 神迷的亮
 * 2018-05-02 15:49
 */
@MappedSuperclass //如果有重复的字段属性要被使用，使用这个注解，让子类继承它，便会有这个类中的字段属性，且不会映射到数据库中去
@Getter
@Setter
public class MapperSuperDate
{
    private Date createTime;

    private Date updateTime;
}
