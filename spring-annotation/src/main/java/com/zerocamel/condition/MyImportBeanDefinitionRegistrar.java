package com.zerocamel.condition;

import com.zerocamel.bean.Grey;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: Spring
 * @description: ${自定义注册类导入类}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-06 23:17
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param annotationMetadata 当前类的注解信息
     * @param beanDefinitionRegistry 手工注册类
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        /**
         * 注意Bean的名字的书写是全类名还是BeanId
         */
        boolean red = beanDefinitionRegistry.containsBeanDefinition("red");
        boolean blue = beanDefinitionRegistry.containsBeanDefinition("blue");
        if (red&&blue)
        {
            //指定Bean的定义信息
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Grey.class);
            beanDefinitionRegistry.registerBeanDefinition("grey",beanDefinition);
        }
    }
}
