package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.UserDto;
import com.sangeng.domain.dto.UserStatusDto;
import com.sangeng.domain.entity.User;

/**
* @author 我是你爸爸
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-04-11 23:20:08
*/
public interface UserService extends IService<User> {


    /**
     * @param pageNum     页码
     * @param pageSize    每页条数
     * @param userName    用户名
     * @param phonenumber 用户电话号码
     * @param status      用户状态
     * @return
     */
    ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    /**
     * 新增用户
     *
     * @param userDto 新增的用户信息
     */
    ResponseResult add(UserDto userDto);

    /**
     * 删除用户
     * @param userId 用户Id
     * @return
     */
    ResponseResult delete(Long userId);

    /**
     * 查询用户信息，查询用户所关联的角色Id，还有所有的角色信息，还有用户信息
     * @return
     */
    ResponseResult userInfo(Long userId);

    /**
     * 更新用户信息
     * @param userDto 更新之后的用户信息
     * @return
     */
    ResponseResult updateUser(UserDto userDto);

    /**
     * 改变用户状态
     * @param userStatusDto 改变的状态信息
     * @return
     */
    ResponseResult changeStatus(UserStatusDto userStatusDto);
}
