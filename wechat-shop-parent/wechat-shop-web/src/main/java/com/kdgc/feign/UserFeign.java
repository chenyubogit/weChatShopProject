package com.kdgc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.kdgc.api.service.UserService;

/**
 * @author Mr.Chen
 * @Classname UserFeign
 * @Description user服务通过feign通讯
 * @Date 2019/3/31 18:59
 */
@FeignClient("member")
public interface UserFeign extends UserService {
    /**
     * 使用token查询用户信息 不继承UserService 则需要复写该段代码
     * 
     * @param token
     * @return
     */
    /*
     * @PostMapping("/member/getUser") Map<String, Object>
     * getUser(@RequestParam("token") String token);
     */
}
