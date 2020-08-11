package com.zerocamel.ext;

import com.zerocamel.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springannotation
 * @description: 扩展原理
 * @author: zeroCamel
 * @create: 2020-08-11 10:28
 *
 * BeanPostProcessor bean后置处理器 bean对象创建初始化前后进行拦截工作
 * BeanFactoryProcessor beanfactory的后置处理器 在BeanFactory标准初始化前后；所有的Bean定义已经保存加载到BeanFactory,但是bean的实例还未创建
 *
 * 1、IOC容器创建对象
 * 2、this.invokeBeanFactoryPostProcessors(beanFactory);
 *      1>如何找到所有的BeanfactoryPostProcessor并执行他们的方法
 *        直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的额方法
 *      2>在初始化创建其他组件之前执行
 *
 * 3、interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry()
 *    执行时机：在所有bean定义信息将要被加载，baen实例还未创建的时候
 *      BeanFactoryProcessor() 之前
 *      优先BeanFactoryPostProcessor执行
 *      利用BeanDefinitionRegitryPostProcessor给容器中再额外添加一些组件
 *
 * 4、ApplicationListener：监听容器中发布的事件。事件驱动模型开发
 *      public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *    自定义监听器：
 *      1>写一个监听器来监听某个事件-ApplicationEvent以及子类
 *          1-继承接口
 *          2-注解@EventListener
 *      2>把监听器加入到容器
 *      3>只要容器中有相关事件的发布，我们就能监听这个事件
 *          ContextRefreshedEvent
 *          ContextClosedEvent
 *      4>发布事件
 *
 * 原理：
 *    ContextRefreshedEvent ContextClosedEvent
 *    1、容器创建对象 刷新对象 finish publishEvent()
 *    2、事件发布流程
 *       1>获取事件的多播器（派发器）getApplicationEventMulticaster()
 *       2>multicastEvent-派发事件
 *       3>获取所有的ApplicationListener  Executor 异步派发事件
 *       4>invokeListener 执行Listener
 *       5>回调onApplicationEvent方法
 *    【事件派发器流程】
 *          1>容器创建对象
 *          2>initApplicationEventMulticaster
 *    【注册监听器】
 *          1>容器初始化 registerListeners()
 *    【注解】
 *          EventListenerMethodProcessor
 *          SmartInitializingSingleton->afterSingletonsInstantiated
 *          1>IOC容器初始化 finishBeanFactoryInitialization(beanFactory)
 *          2>先创建所有的单实例bean:getBean()
 *          3>再次创建好的Bean，判断是否是SmartInitializingSingleton类型的
 *            如果满足就调用afterSingletonsInstantiated()
 *          4>并且将监听器添加到容器中
 *
 **/
@ComponentScan(value = "com.zerocamel.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue()
    {
        return  new Blue();
    }

}
