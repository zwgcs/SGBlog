package com.sangeng.service.impl;

import com.sangeng.constants.SystemConstants;
import com.sangeng.service.PermissionService;
import com.sangeng.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/16 11:40
 * @Description
 * @Since version 1.0
 */
@Service("ps")
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private SecurityUtils securityUtils;
    @Override
    public boolean hasPermission(String permission) {
        //如果当前用户是超级管理员，则直接给予权限
        if(securityUtils.isAdmin()){
            return true;
        }else {
            //如果是管理员，则获取权限
            if(SystemConstants.ADMIN.equals(securityUtils.getUserLogin().getUser().getType())){
                //否则需要从UserLogin对象中获取用户权限
                List<String> authorityList = securityUtils.getUserLogin().getAuthorityList();
                return authorityList.contains(permission);
            }
            //如果是普通用户则没有任何权限
            return false;
        }
    }
}
