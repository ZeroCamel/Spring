package com.zerocamel.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @program: springannotation
 * @description: 自定义bean创建工厂
 * @author: zeroCamel
 * @create: 2020-08-11 10:33
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor...Created");
        int definitionCount = configurableListableBeanFactory.getBeanDefinitionCount();
        System.out.println("DefinitionBeanCount:"+definitionCount);
        String[] definitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        for (String beanName:definitionNames) {
            System.out.println(beanName);
        }

    }
}
