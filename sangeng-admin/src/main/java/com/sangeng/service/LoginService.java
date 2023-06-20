package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

/**
 * 进行登录与退出登录业务
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/12 19:07
 * @Since version 1.0
 */
public interface LoginService {
    /**
     * 登录业务
     * @param user
     * @return
     */
    ResponseResult login(User user);

    /**
     * 退出登录
     * @return
     */
    ResponseResult logout();

    /**
     * 获取当前用户的权限信息
     * @return
     */
    ResponseResult getInfo();

    /**
     * 获取当前用户的路由信息
     * @return
     */
    ResponseResult getRouters();
}
