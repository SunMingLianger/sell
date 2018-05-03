package com.sml.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 神迷的亮
 * 2018-05-02 15:48
 */
@Data
@DynamicUpdate
@Entity
public class SellerInfo extends MapperSuperDate
{

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}