package com.kdgc.common.entity;

import org.apache.commons.net.ntp.TimeStamp;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mr.Chen
 * @Classname BaseEntity
 * @Description 封装一些相同的字段和属性
 * @Date 2019/3/28 19:53
 */
@Getter
@Setter
@Slf4j
public class BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private TimeStamp created;

    /**
     * 修改时间
     */
    private TimeStamp updated;
}
