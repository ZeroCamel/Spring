package com.zerocamel.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @program: spring-servlet
 * @description: ${三大组件之-监听器}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 12:00
 **/
public class UserListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        System.out.println("UserListener...contextInitialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("UserListener...contextDestroyed...");
    }
}
