package com.kdgc.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import org.apache.ibatis.jdbc.SQL;

import com.kdgc.common.entity.UserEntity;

/**
 * @author Mr.Chen
 * @Classname ReflectionUtils
 * @Description 反射工具类
 * @Date 2019/3/28 20:30
 */
public class ReflectionUtils {

    /**
     * 封装当前类和父类的属性、拼接SQL
     *
     * @return
     */
    public static String fatherAndSonField(Object object) {
        if (object == null) {
            return null;
        }
        // 获取class文件
        Class<?> classInfo = object.getClass();
        Field[] sonFields = classInfo.getDeclaredFields();
        String sonFieldSql = getField(sonFields);
        Field[] fatehrFields = classInfo.getSuperclass().getDeclaredFields();
        String fatehrFieldSql = getField(fatehrFields);
        return sonFieldSql + "," + fatehrFieldSql;
    }

    /**
     * 封装当前类和父类的属性值、拼接SQL
     *
     * @param object
     * @return
     */
    public static String fatherAndSonFieldValue(Object object) {
        if (object == null) {
            return null;
        }
        // 获取class文件
        Class<?> classInfo = object.getClass();
        Field[] sonFields = classInfo.getDeclaredFields();
        String sonFieldSql = getFieldValue(sonFields, object);
        Field[] fatehrFields = classInfo.getSuperclass().getDeclaredFields();
        String fatehrFieldSql = getFieldValue(fatehrFields, object);
        return sonFieldSql + "," + fatehrFieldSql;
    }

    /**
     * @return
     * @Description 获取类的属性、拼接属性sql、原始方法
     */
    /*
     * public static String getInsertFileds(Object object) { if (object == null) {
     * return null; } // 获取class文件 Class<?> classInfo = object.getClass();
     * //classInfo.getSuperclass().getDeclaredFields()+classInfo.getDeclaredFields()
     * // 拿到父类属性和子类属性 Field[] declaredFields = classInfo.getDeclaredFields()//
     * 获取当前类的属性集合 StringBuffer sf = new StringBuffer(); for (int i = 0; i <
     * declaredFields.length; i++) { sf.append(declaredFields[i].getName()); if (i <
     * declaredFields.length - 1) { sf.append(","); } }; return sf.toString(); }
     */
    /**
     * 封装返回的SQL语句
     *
     * @param declaredFields
     * @return
     */
    public static String getField(Field[] declaredFields) {
        StringBuffer sf = new StringBuffer();
        // 循环获取当前类的属性、并进行拼接、eg：age，name
        for (int i = 0; i < declaredFields.length; i++) {
            sf.append(declaredFields[i].getName());
            if (i < declaredFields.length - 1) {
                sf.append(",");
            }
        }
        return sf.toString();
    }

    /**
     * 封装返回的SQL的值
     *
     * @param declaredFields
     * @return
     */
    public static String getFieldValue(Field[] declaredFields, Object object) {
        StringBuffer sf = new StringBuffer();
        // 循环获取当前类的属性、并进行拼接、eg：age，name
        for (int i = 0; i < declaredFields.length; i++) {
            // 获取到属性值
            try {
                Field field = declaredFields[i];
                // 允许操作私有属性
                field.setAccessible(true);
                Object value = field.get(object);
                // 每次append时添加'防止属性值有例如 张三， 这类数据造成的执行SQL报错
                boolean flag = false;
                if (value != null && (value instanceof String || value instanceof Timestamp)) {
                    flag = true;
                }
                if (flag) {
                    sf.append("'" + value + "'");
                } else {
                    sf.append(value);
                }
                if (i < declaredFields.length - 1) {
                    sf.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sf.toString();
    }

    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("张三");
        userEntity.setPhone("18538165238");
        userEntity.setEmail("326184115@qq.com");
        userEntity.setPassword("123456");
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        final String filed = fatherAndSonField(userEntity);
        final String value = fatherAndSonFieldValue(userEntity);
        SQL sql = new SQL() {

            {
                INSERT_INTO("mb_user");
                VALUES(filed, value);
            }
        };
        System.out.println(sql);
    }
}
