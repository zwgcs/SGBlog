
package com.sangeng.service.impl;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.UserLogin;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.UserInfoVo;
import com.sangeng.domain.vo.UserLoginVo;
import com.sangeng.service.BlogLoginService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.JwtUtil;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;



    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户认证失败，请重新登录");
        } else {
            UserLogin userLogin = (UserLogin)authenticate.getPrincipal();
            String userId = userLogin.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            this.redisCache.setCacheObject("userId:" + userId, userLogin);
            UserInfoVo userInfoVo = BeanCopyUtils.copyBean(userLogin.getUser(), UserInfoVo.class);
            UserLoginVo userLoginVo = new UserLoginVo(jwt, userInfoVo);
            return ResponseResult.okResult(userLoginVo);
        }
    }

    @Override
    public ResponseResult loginOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLogin userLogin = (UserLogin)authentication.getPrincipal();
        String userId = userLogin.getUser().getId().toString();
        this.redisCache.deleteObject("userId:" + userId);
        return ResponseResult.okResult();
    }
}
