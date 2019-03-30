package com.kdgc.common.entity;

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

    private String username;

    private String password;

    private String phone;

    private String email;
}
