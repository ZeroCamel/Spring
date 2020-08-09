package com.zerocamel.config;

import com.zerocamel.aop.LogAspects;
import com.zerocamel.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: Spring
 * @description: ${AOP
 * 原理：动态代理
 * 运行时多态 在程序运行期间将某代码切入到指定方法指定位置进行运行的编程方式
 * 嵌入到整个Bean的生命周期包括环境准备、方法监控、善后处理具体的应用场景包括
 * 1>日志记录 2>事务控制 3>权限管控
 *
 * 1、导入模块
 * 2、定义业务逻辑处理类
 * 3、定义一个切面类LoaAspects
 *    通知：
 *      前置通知(@Before)：目标方法运行之前
 *      后置通知(@After)：目标方法运行之后
 *      返回通知(@AfterReturning)：目标方法返回之后
 *      异常通知(@AfterThrowing)：目标方法出现异常之后
 *      环绕通知(@Around)：动态代理，手动推进目标方法运行（JoinPoint.Proceed）
 * 4、给切面类的目标方法标注何时何地运行(通知注释)
 * 5、将切面类和业务逻辑处理类加入到容器中
 * 6、使用@Aspect注解指明切面类
 * [7]、开启基于注解的AOP@EnableAspectJAutoProxy
 * }
 * @author: Mr.ZeroCamel
 * @create: 2020-08-09 14:10
 **/
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {
    @Bean
    public MathCalculator mathCalculator()
    {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects()
    {
        return new LogAspects();
    }
}
