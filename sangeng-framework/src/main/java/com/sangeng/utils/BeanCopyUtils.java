package com.sangeng.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/17 17:19
 * @Description 一个用于将实体类转为对应的VO类的工具类
 * @Since version 1.0
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    public static <T> T copyBean(Object source,Class<T> clazz){
        T result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source,result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

        public static <T,O> List<T> copyBean(List<O> list,Class<T> clazz){
        return list.stream()
                .map(o->copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}