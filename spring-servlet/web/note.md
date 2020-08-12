#### 过程分析
***
* 容器在启动应用的时候，会扫描当前应用每一个Jar包里面
META-INF/Services/javax.servlet.ServletContainerInitializer
1、使用servletcontext注册web组件
2、使用编码的方式，在项目启动的时候添加组件
   1 ServletContainerInitializer得到
   2 ServletContextListener
     contextInitialized->servletContextEvent.getServletContext();
    