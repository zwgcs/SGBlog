package com.sangeng.controller;

import com.sangeng.annotations.LogPrint;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;
import com.sangeng.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 处理用户的相关请求
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/3/31 7:43
 * @Since version 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户",description = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @LogPrint(BusinessName = "用户信息接口")
    @ApiOperation(value = "用户信息",notes = "查询用户信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @LogPrint(BusinessName = "更新用户信息接口")
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @LogPrint(BusinessName = "用户注册接口")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public ResponseResult register(@RequestBody User user){
        return  userService.register(user);
    }
}
