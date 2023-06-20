package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.UserDto;
import com.sangeng.domain.dto.UserStatusDto;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.entity.UserRole;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleVo1;
import com.sangeng.domain.vo.UserVo;
import com.sangeng.domain.vo.UserVo1;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.RoleService;
import com.sangeng.service.UserRoleService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 我是你爸爸
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-04-11 23:20:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;
    @Override
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(userName),User::getUserName,userName);
        wrapper.eq(StringUtils.hasText(phonenumber),User::getPhonenumber,phonenumber);
        wrapper.eq(StringUtils.hasText(status),User::getStatus,status);
        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<User> userList = page.getRecords();
        List<UserVo> userVos = BeanCopyUtils.copyBean(userList, UserVo.class);
        return ResponseResult.okResult(new PageVo<UserVo>(userVos,page.getTotal()));
    }

    @Override
    public ResponseResult add(UserDto userDto) {
        if(!StringUtils.hasText(userDto.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if(isUserNameExist(userDto.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(StringUtils.hasText(userDto.getEmail()) && isEmailExist(userDto.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if(StringUtils.hasText(userDto.getPhonenumber()) && isphoneNumberExist(userDto.getPhonenumber())){
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        save(user);
        List<String> roleIds = userDto.getRoleIds();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userDto.getUserName());
        User user1 = getOne(wrapper);
        Long userId = user1.getId();
        List<UserRole> userRoleList = roleIds.stream().map(rid -> new UserRole(userId, Long.parseLong(rid))).collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Long userId) {
        removeById(userId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userInfo(Long userId) {
        //查询用户所关联的角色Id
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId);
        List<UserRole> userRoles = userRoleService.list(wrapper);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        //查询所有角色信息
        List<Role> roles = roleService.list();
        List<RoleVo1> roleVo1s = BeanCopyUtils.copyBean(roles, RoleVo1.class);
        //查询用户信息
        User user = getById(userId);
        UserVo1 userVo1 = BeanCopyUtils.copyBean(user, UserVo1.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("roleIds",roleIds);
        map.put("roles",roleVo1s);
        map.put("user",userVo1);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult updateUser(UserDto userDto) {
        User user = new User();
        user.setId(Long.parseLong(userDto.getId()));
        user.setPhonenumber(userDto.getPhonenumber());
        user.setEmail(userDto.getEmail());
        user.setSex(userDto.getSex());
        user.setStatus(userDto.getStatus());
        user.setNickName(userDto.getNickName());
        updateById(user);
        //先删除表中原有的user-role
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,Long.parseLong(userDto.getId()));
        userRoleService.remove(wrapper);
        List<UserRole> userRoles = userDto.getRoleIds().stream().map(rid -> new UserRole(Long.parseLong(userDto.getId()), Long.parseLong(rid))).collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult changeStatus(UserStatusDto userStatusDto) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,userStatusDto.getUserId());
        wrapper.set(User::getStatus,userStatusDto.getStatus());
        update(wrapper);
        return ResponseResult.okResult();
    }

    private boolean isUserNameExist(String userName){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        int count = count(wrapper);
        return count !=0;
    }
    private boolean isEmailExist(String email){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        int count = count(wrapper);
        return count !=0;
    }
    private boolean isphoneNumberExist(String phoneNumber){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhonenumber,phoneNumber);
        int count = count(wrapper);
        return count !=0;
    }

}




