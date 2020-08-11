package com.zerocamel.ext;

import com.zerocamel.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @program: springannotation
 * @description: Bean定义注册 后置处理器
 * @author: zeroCamel
 * @create: 2020-08-11 11:25
 **/
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按照定义注册中心创建Bean
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry..bean的数量："+beanDefinitionRegistry.getBeanDefinitionCount());
//        RootBeanDefinition definitionBuilder = new RootBeanDefinition(Blue.class);

        AbstractBeanDefinition definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();

        beanDefinitionRegistry.registerBeanDefinition("hello",definitionBuilder);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory...bean的数量:"+configurableListableBeanFactory.getBeanDefinitionCount());
    }
}
