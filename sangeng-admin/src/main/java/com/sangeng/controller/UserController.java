package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.UserDto;
import com.sangeng.domain.dto.UserStatusDto;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 22:24
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/system/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/list")
    ResponseResult list(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.userList(pageNum,pageSize,userName,phonenumber,status);
    }

    @PostMapping
    ResponseResult add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }
    @DeleteMapping("/{userId}")
    ResponseResult delete(@PathVariable Long userId){
        return userService.delete(userId);
    }

    @GetMapping("/{userId}")
    ResponseResult info(@PathVariable Long userId){
        return userService.userInfo(userId);
    }

    @PutMapping
    ResponseResult update(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @PutMapping("/changeStatus")
    ResponseResult changStatus(@RequestBody UserStatusDto userStatusDto){
        return userService.changeStatus(userStatusDto);
    }

}
