package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.UserLogin;
import com.sangeng.domain.entity.User;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/12 19:16
 * @Description
 * @Since version 1.0
 */
@Component
public class UserNameServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //通过用户名查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        User user = userMapper.selectOne(wrapper);
        if(Objects.isNull(user)){
            //如果用户不存在
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR,"用户名不存在！！！！");
        }
        //如果当前用户是管理员，则还需要返回管理员的权限信息
        if(user.getType().equals(SystemConstants.ADMIN)){
            return new UserLogin(user,menuService.getPerms(user.getId()));
        }
        return new UserLogin(user,null);
    }
}
