package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.entity.RoleMenu;
import com.sangeng.domain.vo.MenuVo1;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.service.MenuService;
import com.sangeng.service.RoleMenuService;
import com.sangeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 我是你爸爸
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-04-11 23:20:08
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;


    @Autowired
    private RoleService roleService;


    @Override
    public List<Long> getMenuIds(Long userId) {
        //通过用户Id查询到角色Id
        List<Long> roleIds = roleService.getRoleIds(userId);
        //通过角色Id查询到菜单Id
        List<List<Long>> menuIds1 = roleIds.stream().map(rid -> {
            LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RoleMenu::getRoleId, rid);
            List<Long> menuIds = roleMenuService.list(wrapper).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
            return menuIds;
        }).collect(Collectors.toList());
        HashSet<Long> menuIds = new HashSet<>();
        //因为是所有角色对应的菜单，角色菜单之间可能会存在重复，因此得去重
        menuIds1.stream().forEach(list->list.stream().forEach(menuIds::add));
        //过滤掉一些非法条件
        List<Long> newMenuIds = menuIds.stream()
                //过滤掉对应的菜单不可用的菜单Id
                .filter(mid -> SystemConstants.MENU_STATUS_NORMAL.equals(getById(mid).getStatus()))
                //过滤掉菜单不存在的
                .filter(mid -> getById(mid) != null)
                .collect(Collectors.toList());
        return newMenuIds;
    }

    @Override
    public List<String> getPerms(Long userId) {
        if(userId.equals(1L)){
            //如果当前用户是超级管理员则直接查询所有权限即可
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            //查询权限是给后端进行权限校验使用的，因此只需要查询菜单和按钮
            wrapper.in(Menu::getMenuType,"C","F");
            wrapper.eq(Menu::getStatus,SystemConstants.MENU_STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> collect = menus.stream().map(Menu::getPerms).collect(Collectors.toList());
            return collect;
        }
        //如果当前用户不是超级管理员，则"联表"查询
        List<Long> menuIds = getMenuIds(userId);
        //将菜单Id对应的菜单类型为目录过滤掉
        List<Long> newMenuIds = menuIds.stream().filter(
                mid -> !"M".equals(getById(mid).getMenuType())
        ).collect(Collectors.toList());
        //通过菜单Id查询到菜单权限关键字
        List<String> menuPerms = newMenuIds.stream().map(id -> getById(id).getPerms()).collect(Collectors.toList());
        return menuPerms;
    }

    @Override
    public ResponseResult menuList(String status, String menuName) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(menuName),Menu::getMenuName,menuName);
        wrapper.eq(StringUtils.hasText(status),Menu::getStatus,status);
        wrapper.orderByAsc(Menu::getParentId);
        wrapper.orderByAsc(Menu::getOrderNum);
        List<Menu> menuList = list(wrapper);
        return ResponseResult.okResult(menuList);
    }

    @Override
    public ResponseResult add(Menu menu) {
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult menuInfo(Long menuId) {
        Menu menu = getById(menuId);
        return ResponseResult.okResult(menu);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if(menu.getId().equals(menu.getParentId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"修改菜单："+menu.getMenuName()+"错误，上级菜单不能选择自己");
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long menuId) {
        List<Menu> menus = list();
        for(Menu menu:menus){
            if(menu.getParentId().equals(menuId)){
                //return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"存在子菜单不允许删除");
                throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR,"存在子菜单不允许删除");
            }
        }
        removeById(menuId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult treeSelect() {
        List<Menu> menus = list();
        List<Long> menuIds = menus.stream().filter(mid -> getById(mid).getParentId().equals(0L)).map(Menu::getId).collect(Collectors.toList());
        List<MenuVo1> treeMenus = menuIds.stream().map(mid -> getBaseMapper().menuList3(mid)).collect(Collectors.toList());
        return ResponseResult.okResult(treeMenus);
    }

    @Override
    public ResponseResult roleMenuTreeselect(Long roleId) {
        List<Long> menuIds = null;
        //因为数据库中对于超级管理员的关联菜单数据是缺失的，所以如果按照条件去查的话，就会查不到，所以这里一旦是超级管理员的话，我就把它所有的菜单Id给查出来
        if(roleId.equals(1L)){
            menuIds = list().stream().map(Menu::getId).collect(Collectors.toList());
        }else{
            LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RoleMenu::getRoleId,roleId);
            List<RoleMenu> roleMenus = roleMenuService.list(wrapper);
            menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        }
        List<Long> menuIds1 = list().stream().map(Menu::getId).collect(Collectors.toList());
        menuIds1 = menuIds1.stream().filter(mid -> getById(mid).getParentId().equals(0L)).collect(Collectors.toList());
        List<MenuVo1> menuVo1List = menuIds1.stream().map(mid -> getBaseMapper().menuList3(mid)).collect(Collectors.toList());
        HashMap<String, List> map = new HashMap<>();
        map.put("menus",menuVo1List);
        map.put("checkKeys",menuIds);
        return ResponseResult.okResult(map);
    }

}




