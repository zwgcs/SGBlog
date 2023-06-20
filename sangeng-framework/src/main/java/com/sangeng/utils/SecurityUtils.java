package com.sangeng.utils;

import com.sangeng.domain.entity.UserLogin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/2 16:35
 * @Description
 * @Since version 1.0
 */
@Component
public class SecurityUtils {
    /**
     * 获取Authentication
     * @return
     */
    public  Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取UserLogin
     * @return
     */
    public  UserLogin getUserLogin(){
        return (UserLogin) getAuthentication().getPrincipal();
    }
    /**
     * 获取用户Id
     * @return
     */
    public  Long getUserId(){
        return getUserLogin().getUser().getId();
    }

    /**
     * 判断用户是否为管理员
     */
    public  boolean isAdmin(){
        Long id = getUserId();
        return id!=null && id.equals(1L);
    }
}
