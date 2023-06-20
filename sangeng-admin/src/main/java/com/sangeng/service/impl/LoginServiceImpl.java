package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.domain.ResponseResult;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.UserLogin;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.AuthorityInfoVo;
import com.sangeng.domain.vo.MenuVo;
import com.sangeng.domain.vo.UserInfoVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.service.LoginService;
import com.sangeng.service.MenuService;
import com.sangeng.service.RoleService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import com.sangeng.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/4/12 19:09
 * @Description
 * @Since version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResponseResult login(User user) {
        //将用户名和密码封装成UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //调用AuthenticationManager对象的authenticate认证方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证失败，抛出异常
        if(Objects.isNull(authenticate)){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //通过authenticate对象拿到用户信息
        UserLogin userLogin = (UserLogin)authenticate.getPrincipal();
        //将用户的Id加密生成token
        Long userId  = userLogin.getUser().getId();

        String jwt = JwtUtil.createJWT(userId.toString());

        //将用户信息存入Redis中
        redisCache.setCacheObject("userid:"+userId,userLogin);

        //将token进行返回
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }



    @Override
    public ResponseResult logout() {
        Long userId = securityUtils.getUserId();
        String userid = "userid:"+userId;
        redisCache.deleteObject(userid);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getInfo() {
        User user = securityUtils.getUserLogin().getUser();
        //查询到当前用户的所有角色关键字
        List<String> roleKeys = roleService.getRoleKeys(user.getId());
        //查询到当前用户的所有菜单权限关键字
        List<String> menuPerms = menuService.getPerms(user.getId());
        //获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据
        AuthorityInfoVo authorityInfoVo = new AuthorityInfoVo(menuPerms, roleKeys, userInfoVo);
        return ResponseResult.okResult(authorityInfoVo);
    }

    @Override
    public ResponseResult getRouters() {
        List<Long> menuIds = null;
        //如果当前用户是超级管理员的话，返回除按钮的所有menu
        if(securityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            //查询出当前菜单可用的
            wrapper.eq(Menu::getStatus, SystemConstants.MENU_STATUS_NORMAL);
            //只需要查询出菜单类型为目录和菜单，因为这是路由信息，交由前端处理，按钮是不需要管的，反正后端会控制操作按钮的权限的
            wrapper.in(Menu::getMenuType,"M","C");
            //查询出满足条件的所有菜单Id
            menuIds = menuService.list(wrapper).stream().map(Menu::getId).collect(Collectors.toList());
        }else{
            Long userId = securityUtils.getUserId();
            //如果当前用户不是超级管理员，则"联表查询"
            menuIds = menuService.getMenuIds(userId);
        }
        //查询出所有的目录Id，也就是parentId为0的菜单
        List<Long> actualMenuIds = menuIds.stream().filter(menuId -> menuService.getById(menuId).getParentId().equals(0L)).collect(Collectors.toList());
        //根据目录Id，查询出每个目录及其目录下的子菜单
        List<MenuVo> list = actualMenuIds.stream().map(menuId -> menuMapper.menuList1(menuId)).collect(Collectors.toList());
        Map<String,List> map = new HashMap<>();
        map.put("menus",list);
        return ResponseResult.okResult(map);
    }
}
