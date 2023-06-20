package com.sangeng.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.sangeng.annotations.LogPrint;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author sq Email:1718048299@qq.com
 * @Date 2023/4/6 16:02
 * @Since version 1.0
 */
@Service
@Aspect
@Slf4j
public class AopLogServiceImpl implements AopLogService {


    /**
     * 设置切点，使其增强作用在有指定注解的方法上
     */
    @Pointcut("@annotation(com.sangeng.annotations.LogPrint)")
    public void pt(){

    }

    /**
     * 打印日志
     * @param joinPoint 连接点
     * @return
     * @throws Throwable
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        try {
            beforeLog(joinPoint);
            res = joinPoint.proceed();
            afterLog(res);
        } finally {
            //结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return res;
    }


    /**
     * 前置日志打印
     * @param joinPoint 连接点对象
     */
    @Override
    public void beforeLog(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取注解对象
        LogPrint logPrint = getAnnotation(joinPoint);


        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", logPrint.BusinessName());
        // 打印 Http method
        log.info("HTTP Method    : {}",request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",joinPoint.getSignature().getDeclaringTypeName(),((MethodSignature)joinPoint.getSignature()).getName() );
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteAddr());
        // 打印请求入参
        Object[] args = joinPoint.getArgs();
        boolean flag = true;
        for(Object arg : args){
            if(arg instanceof MultipartFile){
                flag = false;
            }
        }
        if(flag){
            log.info("Request Args   : {}",JSON.toJSONString(joinPoint.getArgs()));
        }

    }

    /**
     * 获取方法上面的注解对象
     * @param joinPoint 方法连接点对象
     * @return
     */
    private LogPrint getAnnotation(ProceedingJoinPoint joinPoint) {
        //先获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //通过方法签名获得方法
        Method method = signature.getMethod();
        //通过方法获取方法上面的注解
        LogPrint logPrint = method.getAnnotation(LogPrint.class);
        return logPrint;
    }

    /**
     * 后置日志打印
     * @param res
     */
    @Override
    public void afterLog(Object res) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(res));
    }
}
