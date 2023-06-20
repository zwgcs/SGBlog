package com.sangeng.controller;


import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.service.BlogLoginService;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理登录与登出请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/2 23:38
 * @Since version 1.0
 */
@RestController
@Api(tags = "登录与登出",description = "前台登录与登出相关接口")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;


    @PostMapping("/login")
    @LogPrint(BusinessName = "登录接口")
    @ApiOperation(value = "登录",notes = "前台用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!Strings.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    @LogPrint(BusinessName = "用户退出登录接口")
    @ApiOperation(value = "登出",notes = "前台用户退出登录")
    public ResponseResult loginOut(){
        return blogLoginService.loginOut();
    }



    @GetMapping("/test")
    public String test(HttpServletResponse response){
        response.setHeader("jieiwi","123");
        return "hello";
    }
}
