package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.entity.UserRole;
import com.sangeng.mapper.UserRoleMapper;
import com.sangeng.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 我是你爸爸
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2023-04-11 23:20:08
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}




