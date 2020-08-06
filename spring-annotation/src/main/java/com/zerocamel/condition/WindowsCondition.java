package com.zerocamel.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @program: springannotation
 * @description: 判断是否是Window注解
 * @author: zeroCamel
 * @create: 2020-08-06 15:52
 **/
public class WindowsCondition implements Condition {

    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        //获取执行环境
        Environment environment = conditionContext.getEnvironment();

        if (environment.getProperty("os.name").contains("Windows"))
        {
            return true;
        }

        return false;
    }
}