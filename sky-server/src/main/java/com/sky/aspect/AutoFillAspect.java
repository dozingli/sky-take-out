package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void before(JoinPoint joinPoint) throws Exception {

        // TODO clarify

        // method
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info(method.getName());

        // update or insert
        AutoFill annotation = method.getAnnotation(AutoFill.class);
        OperationType value = annotation.value();

        Object[] args = joinPoint.getArgs();
        if (args == null && args.length == 0) {
            return;
        }
        Object object = args[0];
        log.info(object.toString());
        log.info(object.getClass().getName());

        Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
        Method setCreateUser = object.getClass().getDeclaredMethod("setCreateUser", Long.class);
        Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
        Method setUpdateUser = object.getClass().getDeclaredMethod("setUpdateUser", Long.class);

        log.info(setCreateTime.toString());
        log.info(setCreateUser.toString());
        log.info(setUpdateTime.toString());
        log.info(setUpdateUser.toString());

        if (value == OperationType.INSERT) {
            setCreateTime.setAccessible(true);
            setCreateUser.setAccessible(true);
            setUpdateTime.setAccessible(true);
            setUpdateUser.setAccessible(true);

            setCreateTime.invoke(object, LocalDateTime.now());
            setCreateUser.invoke(object, BaseContext.getCurrentId());
            setUpdateTime.invoke(object, LocalDateTime.now());
            setUpdateUser.invoke(object, BaseContext.getCurrentId());
        }

        if (value == OperationType.UPDATE) {
            setUpdateTime.setAccessible(true);
            setUpdateUser.setAccessible(true);

            setUpdateUser.invoke(object, BaseContext.getCurrentId());
            setUpdateTime.invoke(object, LocalDateTime.now());
        }
    }
}
