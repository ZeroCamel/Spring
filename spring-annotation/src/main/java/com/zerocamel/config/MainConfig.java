package com.zerocamel.config;

import com.zerocamel.bean.Person;
import com.zerocamel.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @program: springannotation
 * @description: 测试配置类1
 * 1、配置类==配置文件
 * 2、指定扫描规则excludeFilters-排除
 * 3、ComponentScans 指定扫描组
 * 4、FilterType.ANNOTATION 基于注解
 *    FilterTypeASSIGNABLE_TYPE 基于指定类型
 *    FilterTypeRegex 基于正则
 *    FilterTypeCustom 基于自定义
 * 5、
 * @author: zeroCamel
 * @create: 2020-08-06 13:27
 **/
//@ComponentScan(value = "com.zerocamel",excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {
//                Controller.class, Service.class
//        })
//})
@ComponentScans(
        value = {
                @ComponentScan(value = "com.zerocamel",includeFilters = {
//                        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {
//                                Controller.class, Service.class
//                        }),
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {
//                                BookService.class
//                        }),
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes={
                                MyTypeFilter.class
                        })
                },useDefaultFilters = false)
        }
)
@Configuration
public class MainConfig {

    /**
     * TODO:给容器注入BEAN 与XML类似，id默认是方法名
     * 1、默认单实例注入
     * @return
     */
    @Bean(value = "personConName")
    public Person person()
    {
        return  new Person("HH",13);
    }

}
