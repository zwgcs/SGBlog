package com.sangeng.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sangeng.domain.entity.UserLogin;
import com.sangeng.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 插入、更新处理器
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/2 16:41
 * @Since version 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId  ;
        if(! (securityUtils.getAuthentication().getPrincipal() instanceof UserLogin)){
            userId = -1L;
        }else{
            userId = securityUtils.getUserId();
        }
        setFieldValByName("createTime",new Date(),metaObject);
        setFieldValByName("createBy",userId,metaObject);
        setFieldValByName("updateTime",new Date(),metaObject);
        setFieldValByName("updateBy",userId,metaObject);
        setFieldValByName("delFlag",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId ;
        if(securityUtils.getAuthentication() == null || ! (securityUtils.getAuthentication().getPrincipal() instanceof UserLogin)){
            userId = -1L;
        }else{
            userId = securityUtils.getUserId();
        }
        setFieldValByName("updateTime",new Date(),metaObject);
        setFieldValByName("updateBy",userId,metaObject);
        setFieldValByName("delFlag",0,metaObject);
    }
}
