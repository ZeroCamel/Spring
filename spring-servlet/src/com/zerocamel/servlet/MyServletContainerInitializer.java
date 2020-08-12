package com.zerocamel.servlet;

import com.zerocamel.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @program: spring-servlet
 * @description: ${自定义容器初始化器
 * 1、@HandlesType 传入自定义类型 可以获取当前类型的所有子类
 * 2、使用servletContext 注册三大组件 servlet filter listener
 * }
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 11:21
 **/
@HandlesTypes(value = HelloService.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 应用启动运行
     * @param set
     * @param servletContext 代表当前应用的ServletContext;全局唯一
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class<?> c:set)
        {
            System.out.println(c);
        }

        // 注册组件
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("userServlet", new UserServlet());
        dynamic.addMapping("/user");

        // 添加监听器
        servletContext.addListener(UserListener.class);

        // 添加过滤器
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        // 配置Filter的映射信息 路由映射之后拦截所有请求
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

    }
}
