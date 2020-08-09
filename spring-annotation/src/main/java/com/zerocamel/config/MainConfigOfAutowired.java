package com.zerocamel.config;

import com.zerocamel.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @program: springannotation
 * @description: 自动装配
 *   一、利用DI依赖注入，导入依赖关系并赋值
 *   二、如果有多个相同的BeanId，怎么处理
 *   1、默认优先按照类型去容器中找对应的组件
 *   2、如果找到多个相同类型的组件，再将属性的名称作为组件的ID去容器中寻找。
 *      @Primary 首选 @Qualifier 明确指定
 *   三、还支持使用@Resouce(JSR250)-/@Inject(JSR330)
 *       @Resource 不支持@Primary 不支持required=false
 *       @Inject 支持Primary 不支持required=false
 *
 * @author: zeroCamel
 * @create: 2020-08-07 15:57
 **/
@ComponentScan(value = {"com.zerocamel.service",
        "com.zerocamel.bean","com.zerocamel.dao","com.zerocamel.controller"})
@Configuration
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao()
    {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }
}
