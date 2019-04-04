package com.kdgc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kdgc.common.mybatis.BaseDao;
import com.kdgc.entity.UserEntity;

/**
 * @author Mr.Chen
 * @Classname UserDao
 * @Description 操作数据库类
 * @Date 2019/3/30 11:42
 */
@Mapper
public interface UserDao extends BaseDao {

    /**
     * 查询是否存在该员工
     *
     * @return
     */
    @Select("select * from mb_user where phone = #{phone} and password=#{password}")
    UserEntity getUserPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

    /**
     * 根据userId查询用户信息
     * 
     * @param userId
     * @return
     */
    @Select("select * from mb_user where id = #{userId}")
    UserEntity getUserInfoById(@Param("userId") long userId);

    /**
     * 根据openId查找用户
     * 
     * @param openId
     * @return
     */
    @Select("select * from mb_user where openId = #{openId}")
    UserEntity findUserOpenId(@Param("openId") String openId);
}
