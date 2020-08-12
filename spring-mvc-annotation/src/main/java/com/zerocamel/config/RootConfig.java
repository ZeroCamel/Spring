package com.zerocamel.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Type;

/**
 * @program: spring-servlet
 * @description: ${根容器配置,不扫描Controller}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 17:08
 **/
@ComponentScan(value = "com.zerocamel",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
