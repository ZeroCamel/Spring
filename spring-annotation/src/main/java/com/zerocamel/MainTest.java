package com.zerocamel;

import com.zerocamel.bean.Person;
import com.zerocamel.config.MainConfig;
import com.zerocamel.config.MainConfigOfLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: springannotation
 * @description: 主启动类
 * @author: zeroCamel
 * @create: 2020-08-06 13:18
 **/
public class MainTest {
    public static void main(String[] args)
    {
        // TODO:注入BEAN
        //1、配置实现
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        Object person = applicationContext.getBean("person");
//        System.out.println(person);

        //2、AnnotationConfigApplicationContext
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name: beanNamesForType)
        {
            System.out.println(name);
        }

    }
}
