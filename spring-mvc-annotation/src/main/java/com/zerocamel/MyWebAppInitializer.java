package com.zerocamel;

import com.zerocamel.config.AppConfig;
import com.zerocamel.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 16:56
 **/
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * 获取根容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 获取web容器配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    /**
     * 映射
     * / 拦截所有请求 不包括.jsp
     * /* 拦截所有请求 包括.jsp jsp页面是tomcat的jsp引擎解析的
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
