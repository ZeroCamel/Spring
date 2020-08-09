package com.zerocamel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @program: Spring
 * @description: ${日志切面类}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-09 14:19
 **/
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1、本类引用 直接用pointcut()
     * 2、其他的切面引用 命名空间全名
     * 3、* 所有方法 .. 代表两个参数
     */
    @Pointcut("execution(public int com.zerocamel.aop.MathCalculator.*(..))")
    public void pointCut()
    {

    }

    //指定方法指定参数
    //@Before("public int com.zerocamel.aop.MathCalculator.div(int,int)")
    // 任意方法 任意参数
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName()+" 除法运行...参数列表是："+ Arrays.asList(args));
    }

    @After("com.zerocamel.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint)
    {
        System.out.println(joinPoint.getSignature().getName()+"除法结束...");
    }

    /**
     * returning 注解参数用来接收返回值 value 用来标记切点名称
     * @param result
     */
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result)
    {
        System.out.println(joinPoint.getSignature().getName()+"除法正常返回...运行结果是：{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception)
    {
        System.out.println(joinPoint.getSignature().getName()+"除法异常...异常信息是：{"+exception+"}");
    }
}
