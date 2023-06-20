package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Menu;
import com.sangeng.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 19:20
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    ResponseResult list(String status,String menuName){
        return menuService.menuList(status,menuName);
    }
    @PostMapping
    ResponseResult add(@RequestBody Menu menu){
        return menuService.add(menu);
    }
    @GetMapping("/{menuId}")
    ResponseResult info(@PathVariable Long menuId){
        return menuService.menuInfo(menuId);
    }

    @PutMapping
    ResponseResult update(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    ResponseResult delete (@PathVariable Long menuId){
        return menuService.deleteMenu(menuId);
    }

    @GetMapping("/treeselect")
    ResponseResult treeSelect(){
        return menuService.treeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{roleId}")
    ResponseResult roleMenuTreeselect(@PathVariable Long roleId){
        return menuService.roleMenuTreeselect(roleId);
    }
}
