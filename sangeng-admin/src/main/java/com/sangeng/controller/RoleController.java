package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.domain.dto.CategoryStatusDto;
import com.sangeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 19:59
 * @Description
 * @Since version 1.0
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    ResponseResult list(Integer pageNum,Integer pageSize,String roleName,String status){
        return roleService.menuList(pageNum,pageSize,roleName,status);
    }
    @PutMapping("/changeStatus")
    ResponseResult changStatus(@RequestBody CategoryStatusDto roleStatusDto){
        return roleService.changeStatus(roleStatusDto);
    }

    @PostMapping
    ResponseResult add(@RequestBody RoleDto roleDto){
       return roleService.add(roleDto);
    }

    @GetMapping("/{roleId}")
    ResponseResult info(@PathVariable Long roleId){
        return roleService.roleInfo(roleId);
    }

    @PutMapping
    ResponseResult update(@RequestBody RoleDto roleDto){
        return roleService.updateRole(roleDto);
    }

    @DeleteMapping("/{roleId}")
    ResponseResult delete(@PathVariable Long roleId){
        return roleService.delete(roleId);
    }

    @GetMapping("/listAllRole")
    ResponseResult listAllRole(){
        return roleService.listAllRole();
    }
}
