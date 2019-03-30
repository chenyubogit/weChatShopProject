package com.kdgc.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * @author Mr.Chen
 * @Classname BaseDao
 * @Description 封装mybatis的baseDao工具类
 * @Date 2019/3/28 20:21
 */
public interface BaseDao {

    /**
     * @InsertProvider 注解的作用，自定义sql语句
     */
    @InsertProvider(type = BaseProvider.class, method = "save")
    public void save(@Param("object") Object object, @Param("table") String table);
}
