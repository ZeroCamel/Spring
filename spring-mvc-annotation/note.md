1、默认加载jar包下META_INF/service/javax.servlet.ServletContainerInitializer
2、Spring-mvc中是SpringServletContainerInitializer
  1 @HandlesTypes({WebApplicationInitializer.class})
  2 AbstractContextLoaderInitializer
    registerContextLoaderListener(ServletContext) 利用监听器创建根容器
    this.createRootApplicationContext();
  3 AbstractDispatcherServletInitializer
    1 createServletApplicationContext 创建一个web的IOC容器
    2 createDispatcherServlet -- new DispatcherServlet(servletAppContext);
    3 servletContext.addServlet(servletName, **dispatcherServlet**);
  4 AbstractAnnotationConfigDispatcherServletInitializer 注解方式配置的DispatcherServlet初始化器
    1 createRootApplicationContext() 创建根容器
        this.getRootConfigClasses();传入配置类
    2 createServletApplicationContext 创建一个web的IOC容器
        getServletConfigClasses 获取配置类 注入
3、如果以注解方式启动SpringMvc,继承AbstractAnnotationConfigDispatcherServletInitializer
      实现抽象方法，指定DispatcherServlet

[参考链接]: https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#spring-web	"Spring 官网文档"

