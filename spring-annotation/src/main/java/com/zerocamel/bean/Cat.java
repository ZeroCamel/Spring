package com.zerocamel.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-07 10:05
 **/
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("Cat constructor...");
    }

    public void destroy() throws Exception {
        System.out.println("cat destroy...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet...");
    }
}
