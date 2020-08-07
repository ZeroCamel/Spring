package com.zerocamel.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @program: springannotation
 * @description: 后置处理器:初始化前后进行处理
 * @author: zeroCamel
 * @create: 2020-08-07 10:35
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println(s+" postProcessBeforeInitialization...."+s+"=>"+o);
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println(s+" postProcessAfterInitialization..."+s+"=>"+o);
        return o;
    }
}
