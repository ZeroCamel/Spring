package com.zerocamel.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static com.sun.jmx.snmp.ThreadContext.contains;

/**
 * @program: springannotation
 * @description: 判断是否是linux 注解
 * 1、ConditionContext 判断条件使用的上下文环境
 * 2、AnnotatedTypeMetadata 当前标注的注解信息
 * @author: zeroCamel
 * @create: 2020-08-06 15:51
 **/
public class LinuxCondition implements Condition {

    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        //1、获取IOC使用的BeanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //2、获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //3、获取bean定义的抽象类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        //4、获取执行环境
        Environment environment = conditionContext.getEnvironment();
        String property = environment.getProperty("os.name");

        if (property.contains("linux"))
        {
            return true;
        }

        // 判断是否包含Bean定义并注册Bean
        //registry.containsBeanDefinition("person");
        //registry.registerBeanDefinition();
        return false;
    }
}
