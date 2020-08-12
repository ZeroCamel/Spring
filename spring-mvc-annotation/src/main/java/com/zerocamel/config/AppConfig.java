package com.zerocamel.config;

import com.zerocamel.interceptor.MyFirstInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @program: spring-servlet
 * @description: ${只扫描Controller,禁止使用默认规则，否则不生效}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 17:09
 **/
@ComponentScan(value = "com.zerocamel",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    MyFirstInterceptor myFirstInterceptor;

    /**
     * 配置试图解析器
     * 默认的方法 jsp("/WEB-INF/", ".jsp");
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 可以自动装配 可以实例化
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }
}
