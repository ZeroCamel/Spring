### 一、容器创建刷新流程
***
####  BeanFactory创建以及预准备工作

***

##### 1、prepareRefresh 刷新预处理

​    1>initPropertySources();自定义个性化设置方法
​    2>getEnvironment().validateRequiredProperties();
​    3>earlyApplicationEvents= new LinkedHashSet<ApplicationEvent>();

##### 2、obtainFreshBeanFactory 获取刷新BeanFactory

​    1>refreshBeanFactory 

```java
public GenericApplicationContext() {
  this.beanFactory = new DefaultListableBeanFactory();
}
```

​    2>getBeanFactory() 
​    3>return

##### 3、prepareBeanFactory(beanFactory); 设置工厂

​    1 设置类加载器、表达式解析器
​    2 添加ApplicationContextAwareProcessor 
​    3 ignoreDependencyInterface 设置忽略自动装配接口 EnvironmentAware EmbeddedValueResolverAware...
​    4 registerResolvableDependency -- BeanFactory ApplicationEventPublisher ApplicationContext ResourceLoader
​    5 addBeanPostProcessor -- ApplicationListenerDetector
​    6 添加编译时AspectJ支持
​    7 registerSingleton -- ENVIRONMENT_BEAN_NAME SYSTEM_PROPERTIES_BEAN_NAME SYSTEM_ENVIRONMENT_BEAN_NAME

##### 4、postProcessBeanFactory(beanFactory); 工厂后置处理

​    1 子类通过继承BeanDefinitionRegistryPostProcessor重写这个方法 创建并预准备完成以后做进一步设置

#### 执行后置处理（BeanFactoryPostProcessor|BeanPostProcessor）

***

##### 5、invokeBeanFactoryPostProcessors(beanFactory) 

​      BeanFactory标准初始化之后执行
​      两个接口 BeanDefinitionRegistryPostProcessor BeanFactoryPostProcessor 
       只是保存Bean的定义信息
              
​	 **先执行BeanDefinitionRegistryPostProcessor**
------

​      1 获取所有的BeanDefinitionRegistryPostProcessor
​      2 优先级排序 先执行实现了PriorityOrder优先级接口的BeanDefinitionRegistryPostProcessor
​        registryProcessor.postProcessBeanDefinitionRegistry(registry);
​      3 再执行实现了Ordered顺序接口的BeanDefinitionRegistryPostProcessor;
​        postProcessor.postProcessBeanDefinitionRegistry(registry);
​      4 最后执行没有实现任何优先级或者顺序接口的

​    **后执行BeanFactoryProcessor**

***
​      上述后置处理器执行完毕之后，再执行的方法
​         beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);
​      与上述后置处理器流程一致
​         1 获取所有的BeanDefinitionRegistryPostProcessor
​         2 优先级排序 先执行实现了PriorityOrder优先级接口的BeanDefinitionRegistryPostProcessor
​           registryProcessor.postProcessBeanFactory(registry);
​         3 再执行实现了Ordered顺序接口的BeanDefinitionRegistryPostProcessor;
​           postProcessor.postProcessBeanFactory(registry);
​         4 最后执行没有实现任何优先级或者顺序接口的
​         5 invokeBeanFactoryPostProcessors- 回调后置处理器所有的方法看有无实现Bean的定义

##### 6、registerBeanPostProcessors(beanFactory) -- intercept bean creation -- **BeanPostProcessor**

​    1 获取BeanPostProcessor beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
​    2 按优先级顺序注册（添加至BeanFactory工厂） beanFactory.addBeanPostProcessor(postProcessor);
​    3 最后添加一个后置处理器ApplicationListenerDetector，检查是否是监听并添加到监听器

##### 7、initMessageSource(); 国际化绑定、消息绑定、消息解析

​    1 getBeanFactory();
​    2 beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)
​    如果有赋值MESSAGE_SOURCE_BEAN_NAME
​    如果没有自己创建DelegatingMessageSource
​    可以按照区域信息 取出国际化配置文件中某个Key的值
​    3 把创建好的MessageSource注册在容器中，以后可以获取国际化配置文件的值
​        beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
​        messageSource.getMessage();

##### 8、initApplicationEventMulticaster(); Initialize event multicaster for this context.

​    1 getBeanFactory()
​    2 beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
​      如果没有 默认创建SimpleApplicationEventMulticaster
​    3 注入容器
​    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);

##### 9、onRefresh(); --Initialize other special beans in specific context subclasses.

​    1 // For subclasses: do nothing by default.

##### 10、registerListeners();

​    1 getApplicationListeners()
​    2 getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
​    3 派发之前步骤发生的事件

##### 11、finishBeanFactoryInitialization(beanFactory); 初始化所有的单实例Bean

​    1 beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) 	类型转换组件
​    2 beanFactory.hasEmbeddedValueResolver() 	值解析器
​    3 beanFactory.getBeanNamesForType      *AspectJWeaver*
​    4 beanFactory.setTempClassLoader(null); -- Default is none, simply using the standard bean ClassLoader
​    5 beanFactory.freezeConfiguration(); -- Freeze all bean definitions
​    6 beanFactory.preInstantiateSingletons(); 创建
​        1 List<String> beanNames
​        2 getMergedLocalBeanDefinition(beanName);
​        3 if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit())
​            1 if (isFactoryBean(beanName)) 是否实现FactoryBean的接口
​            2 yes:工厂方法创建对象
​               no:**getBean(beanName)=>doGetBean()**=>
​                1 Object sharedInstance = getSingleton(beanName); 缓存Bean
​                    1 yes bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
​                    2 no if (isPrototypeCurrentlyInCreation(beanName)) {
​                        1 yes exception 循环引用异常
​                        2 no 
​                            1 BeanFactory parentBeanFactory = getParentBeanFactory();
​                            2 markBeanAsCreated(beanName); 标记
​                            3 getMergedLocalBeanDefinition(beanName);
​                            4 mbd.getDependsOn(); 获取当前Bean依赖的Bean
​                                1 yes
​                                        this.registerDependentBean(dep, beanName);
​                                        **getBean(dep)** 递归调用
​                                2 no if (mbd.isSingleton())
​                                    1 yes ObjectFactory->重写getObject->createBean
​                                        1 resolveBeanClass(beanName, mbdToUse); 解析Bean的类型
​                                        2 new RootBeanDefinition(mbd); 如果不为NULL
​                                        3 resolveBeforeInstantiation(beanName, mbdToUse); 提前拦截返回代理对象
​                                           **InstantiationAwareBeanPostProcessor** 触发后置处理器 返回代理对象
​                                        4 如果没有代理对象返回，执行doCreateBean(beanName, mbdToUse, args);否则Return
​                                          1 createBeanInstance(beanName, mbd, args); Instantiate the bean. 创建Bean实例
​                                             使用工厂方法instantiateUsingFactoryMethod(beanName, mbd, args);
​                                               new ConstructorResolver(this).instantiateUsingFactoryMethod(beanName, mbd, explicitArgs);
​                                             或者自动装配
​                                               new ConstructorResolver(this).autowireConstructor(beanName, mbd, ctors, explicitArgs);
​                                             或者构造器
​                                               instantiateBean->getInstantiationStrategy().instantiate(mbd, beanName, parent);
                                           2 applyMergedBeanDefinitionPostProcessors bean创建完成后允许后置处理器修改
                                           3 populateBean(beanName, mbd, instanceWrapper);  Initialize the bean instance.
​                                              1 InstantiationAwareBeanPostProcessor->postProcessAfterInstantiation
                                               2 InstantiationAwareBeanPostProcessor->postProcessPropertyValues
                                               3 applyPropertyValues(beanName, mbd, bw, pvs);
                                           4 initializeBean
                                               1 invokeAwareMethods(beanName, bean); 调用感知
                                               2 applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
                                                 beanProcessor.postProcessBeforeInitialization(result, beanName);
                                               3 invokeInitMethods
                                                 是否实现InitializingBean或者是否自定义初始化方法
                                               4 applyBeanPostProcessorsAfterInitialization
                                                 beanProcessor.postProcessAfterInitialization(result, beanName);
                                           5 registerDisposableBeanIfNecessary 注册销毁Bean方法
                                         5 将创建的Bean添加到缓存singleObjects中     
          4 所有Bean都利用getBean创建完之后，检查所有的Bean是否是
            SmartInitializingSingleton->afterSingletonsInstantiated
            
##### 12、finishRefresh IOC创建完成
   1、initLifecycleProcessor 初始化生命周期 LifecycleProcessor
   2、getLifecycleProcessor().onRefresh(); 回调
   3、publishEvent(new ContextRefreshedEvent(this));
   4、LiveBeansView.registerApplicationContext(this);



















