package com.zerocamel.config;

import com.zerocamel.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: springannotation
 * @description: 属性配置类
 * PropertySource 使用propertySource读取外部配置文件
 * @author: zeroCamel
 * @create: 2020-08-07 15:16
 **/
@PropertySource(value = {"classpath:person.properties"})
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person()
    {
        return new Person();
    }
}
