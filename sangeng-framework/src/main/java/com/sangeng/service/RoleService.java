package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.domain.entity.Role;

import java.util.List;

/**
* @author 我是你爸爸
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-04-11 23:20:08
*/
public interface RoleService extends IService<Role> {
    /**
     * 获取当前用户的角色Id集合
     * @return
     */
    List<Long> getRoleIds(Long userId);

    /**
     * 获取当前用户用户的全部角色关键字
     * @return
     */
    List<String> getRoleKeys(Long userId);

    /**
     * 获取角色列表
     * @param roleName 角色名称
     * @param status 角色状态
     * @param pageNum 页码数
     * @param  pageSize 一页大小
     * @return
     */
    ResponseResult menuList(Integer pageNum,Integer pageSize,String roleName, String status);

    /**
     * 更改角色状态
     * @param roleStatusDto
     * @return
     */
    ResponseResult changeStatus(CategoryStatusDto roleStatusDto);

    /**
     * 添加角色
     * @param roleDto
     * @return
     */
    ResponseResult add(RoleDto roleDto);

    /**
     * 获取角色信息
     * @param roleId 角色Id
     * @return
     */
    ResponseResult roleInfo(Long roleId);

    /**
     * 更新角色信息
     * @param roleDto 更新后的角色信息
     * @return
     */
    ResponseResult updateRole(RoleDto roleDto);

    /**
     * 删除角色
     * @param roleId 角色Id
     * @return
     */
    ResponseResult delete(Long roleId);

    /**
     * 获取状态正常的角色列表
     * @return
     */
    ResponseResult listAllRole();
}
