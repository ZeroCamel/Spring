package com.zerocamel.bean;

import org.springframework.beans.factory.FactoryBean;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description: 颜色Bean创建工厂
 * @author: zeroCamel
 * @create: 2020-08-07 09:05
 **/
public class ColorFactoryBean implements FactoryBean {

    public Object getObject() throws Exception {
        out.println("Color Factory Bean...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否是单例
     * @return
     */
    public boolean isSingleton() {
        return true;
    }
}
