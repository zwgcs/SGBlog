package com.sangeng.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 日志打印业务
 * @Author sq Email:1718048299@qq.com
 * @Date 2023/4/6 16:03
 * @Since version 1.0
 */
public interface AopLogService {
    /**
     * 前置日志打印
     * @param joinPoint 连接点对象
     * @return
     */
    void beforeLog(ProceedingJoinPoint joinPoint);

    /**
     * 后置日志打印
     * @param res
     */
    void afterLog(Object res);
}
