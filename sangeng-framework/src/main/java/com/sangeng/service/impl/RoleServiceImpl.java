package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.entity.RoleMenu;
import com.sangeng.domain.entity.UserRole;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleVo;
import com.sangeng.domain.vo.RoleVo1;
import com.sangeng.mapper.RoleMapper;
import com.sangeng.service.RoleMenuService;
import com.sangeng.service.RoleService;
import com.sangeng.service.UserRoleService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 我是你爸爸
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-04-11 23:20:08
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Long> getRoleIds(Long userId) {
        //通过用户Id查询到角色Id
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId);
        List<UserRole> userRoles = userRoleService.list(wrapper);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        //过滤掉一些非法条件
        roleIds = roleIds.stream()
                //过滤掉对应的角色不可用的角色Id
                .filter(rid -> SystemConstants.ROLE_STATUS_NORMAL.equals(getById(rid).getStatus()))
                //过滤掉角色不存在的
                .filter(rid -> getById(rid)!=null).collect(Collectors.toList());
        return roleIds;
    }

    @Override
    public List<String> getRoleKeys(Long userId) {

        List<String> roleKeys = null;

        if(userId.equals(1L)){
            //如果当前用户是超级管理员则直接添加admin返回即可
            roleKeys = new ArrayList<>();
            roleKeys.add("admin");
        }else{
            //如果当前用户不是超级管理员，则"联表"查询
            List<Long> roleIds = getRoleIds(userId);
            //通过角色Id查询角色关键字
            roleKeys = roleIds.stream().map(id -> getById(id).getRoleKey()).collect(Collectors.toList());
        }
        return roleKeys;
    }

    @Override
    public ResponseResult menuList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(status),Role::getStatus,status);
        wrapper.like(StringUtils.hasText(roleName),Role::getRoleName,roleName);
        wrapper.orderByAsc(Role::getRoleSort);
        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<Role> roleList = page.getRecords();
        List<RoleVo> roleVos = BeanCopyUtils.copyBean(roleList, RoleVo.class);
        return ResponseResult.okResult(new PageVo<RoleVo>(roleVos,page.getTotal()));
    }

    @Override
    public ResponseResult changeStatus(CategoryStatusDto roleStatusDto) {
        LambdaUpdateWrapper<Role> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Role::getId,roleStatusDto.getCategoryId());
        wrapper.set(Role::getStatus,roleStatusDto.getStatus());
        update(wrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult add(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        save(role);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRoleName, roleDto.getRoleName());
        Role role1 = getOne(wrapper);
        Long roleId = role1.getId();
        List<RoleMenu> roleMenuList = roleDto.getMenuIds().stream().map(mid -> new RoleMenu(roleId, Long.parseLong(mid))).collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult roleInfo(Long roleId) {
        Role role = getById(roleId);
        RoleVo roleVo = BeanCopyUtils.copyBean(role, RoleVo.class);
        return ResponseResult.okResult(roleVo);
    }

    @Override
    public ResponseResult updateRole(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        updateById(role);
        List<RoleMenu> roleMenus = roleDto.getMenuIds().stream().map(mid -> new RoleMenu(roleDto.getId(), Long.parseLong(mid))).collect(Collectors.toList());
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,roleDto.getId());
        roleMenuService.remove(wrapper);
        roleMenuService.saveBatch(roleMenus);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Long roleId) {
        removeById(roleId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllRole() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getStatus,SystemConstants.ROLE_STATUS_NORMAL);
        List<Role> roles = list(wrapper);
        List<RoleVo1> roleVoList = BeanCopyUtils.copyBean(roles, RoleVo1.class);
        return ResponseResult.okResult(roleVoList);
    }
}




