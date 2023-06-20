package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;
/**
 * 处理登录登出业务
 * @Author SQ Email:1718048299@qq.com
 * @Since version 1.0
 */
public interface BlogLoginService {
    /**
     * 用户前台登录
     * @param user 用户信息
     * @return
     */
    ResponseResult login(User user);

    /**
     * 用户前台登出
     * @return
     */
    ResponseResult loginOut();
}