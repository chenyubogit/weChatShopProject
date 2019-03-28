package com.kdgc.common.api;

import java.util.HashMap;
import java.util.Map;

import com.kdgc.constants.BaseApiConstants;

/**
 * @author Mr.Chen
 * @Classname BaseApiService
 * @Description 通用baseApi父类
 * @Date 2019/3/27 18:05
 */
public class BaseApiService {

    /**
     * 失败、回传失败信息
     * 
     * @param msg
     * @return
     */
    public Map<String, Object> setResultError(String msg) {
        return setResult(BaseApiConstants.HTTP_500_CODE, msg, null);
    }

    /**
     * 成功、无参
     * 
     * @return
     */
    public Map<String, Object> setResultSucc() {
        return setResult(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, null);
    }

    /**
     * 成功、回传需要返回的数据
     * 
     * @param data
     * @return
     */
    public Map<String, Object> setResultSuccData(Object data) {
        return setResult(BaseApiConstants.HTTP_200_CODE, BaseApiConstants.HTTP_SUCCESS_NAME, data);
    }

    /**
     * 成功、回传需要返回的信息
     * 
     * @param msg
     * @return
     */
    public Map<String, Object> setResultSucc(String msg) {
        return setResult(BaseApiConstants.HTTP_200_CODE, msg, null);
    }

    /**
     * 返回的结果集
     * 
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public Map<String, Object> setResult(Integer code, String msg, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(BaseApiConstants.HTTP_CODE_NAME, code);
        result.put(BaseApiConstants.HTTP_200_NAME, msg);
        if (data != null) {
            result.put(BaseApiConstants.HTTP_DATA_NAME, data);
        }
        return result;
    }
}
