package com.kdgc.common.mybatis;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.kdgc.util.ReflectionUtils;

/**
 * @author Mr.Chen
 * @Classname BaseProvider
 * @Description 自定义SQL的封装
 * @Date 2019/3/28 20:23
 */
public class BaseProvider {

    public String save(Map<String, Object> map) {
        // 实体类
        final Object object = map.get("object");
        // 表名称
        final String table = (String) map.get("table");
        // 生成添加的SQL语句、使用反射机制
        // 步骤：1、使用反射机制拿到该实体类的所有实例
        SQL sql = new SQL() {

            {
                INSERT_INTO(table);
                VALUES(ReflectionUtils.fatherAndSonField(object), ReflectionUtils.fatherAndSonFieldValue(object));
            }
        };
        return sql.toString();
    }
}
