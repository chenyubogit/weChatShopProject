package com.kdgc.entity;

import com.kdgc.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mr.Chen
 * @Classname UserEntity
 * @Description user实体类
 * @Date 2019/3/30 10:01
 */
@Getter
@Setter
public class UserEntity extends BaseEntity {

    private String userName;

    private String password;

    private String phone;

    private String email;

    private String openId;
}
