package com.kdgc.common.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mr.Chen
 * @Classname BaseEntity
 * @Description 封装一些相同的字段和属性
 * @Date 2019/3/28 19:53
 */
@Getter
@Setter
public class BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Timestamp created;

    /**
     * 修改时间
     */
    private Timestamp updated;
}
