package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Menu;

import java.util.List;

/**
* @author 我是你爸爸
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-04-11 23:20:08
*/
public interface MenuService extends IService<Menu> {
    /**
     * 获取当前用户所拥有的权限菜单Id集合
     * @param userId 用户Id
     * @return
     */
    List<Long> getMenuIds(Long userId);

    /**
     * 获取当前用户所拥有的权限标识集合
     *@param userId 用户Id
     * @return
     */
    List<String> getPerms(Long userId);


    /**
     * 可以针对菜单名进行模糊查询，也可以根据状态进行查询
     * @param status 状态
     * @param menuName 菜单名
     * @return
     */
     ResponseResult menuList(String status, String menuName);

    /**
     * 增加菜单
     * @param menu 菜单对象
     * @return
     */
    ResponseResult add(Menu menu);

    /**
     * 查询菜单信息
     * @param menuId 菜单Id
     * @return
     */
    ResponseResult menuInfo(Long menuId);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    ResponseResult updateMenu(Menu menu);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    ResponseResult deleteMenu(Long menuId);

    /**
     * 查询菜单树
     * @return
     */
    ResponseResult treeSelect();

    /**
     * 获取角色的菜单树
     * @param roleId 角色Id
     * @return
     */
    ResponseResult roleMenuTreeselect(Long roleId);
}
