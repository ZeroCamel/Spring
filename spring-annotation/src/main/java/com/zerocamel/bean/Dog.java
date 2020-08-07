package com.zerocamel.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: springannotation
 * @description: 面向切面
 * 1、construct postconstruct ... predestroy destroy
 * 2、
 * @author: zeroCamel
 * @create: 2020-08-07 10:18
 **/
@Component
public class Dog implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public Dog() {
        System.out.println("dog constructor...");
    }

    @PostConstruct
    public void initial()
    {
        System.out.println("dog init....");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog destroy...");
    }

    /**
     * 给容器中注入应用程序上下文
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }
}
