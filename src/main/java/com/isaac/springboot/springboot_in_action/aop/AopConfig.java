package com.isaac.springboot.springboot_in_action.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
/**
 * AOP表达式举例参考
 * execution(public * *(..))--所有public方法,*路径 *方法名
 * execution(* set*(..))--set开头的方法
 * execution(public * set*(..))--set开头的public方法
 * execution(public com.isaac.springboot.springboot_in_action.* set*(..))--指定包下set开头的方法
 * target(com.isaac.springboot.springboot_in_action.service.TestService)--所有实现了TestService接口的类的方法
 * @target（org.springframework.transaction.annotation.Transactional）--所有用@Transactional注解的方法
 * @within(org.springframework.stereotype.Controller)--类型声明了@Controller的所有方法
 *
 */
public class AopConfig {
    //@within 表示目标类型带有的注解
    //@Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            System.out.println("进入AOP，参数是：" + Arrays.asList(args));
            //调用原有方法
            Object o = pjp.proceed();
            System.out.println("调用原方法成功，return:" + o);
            return o;
        } catch (Exception e) {
            throw e;
        }
    }
}
