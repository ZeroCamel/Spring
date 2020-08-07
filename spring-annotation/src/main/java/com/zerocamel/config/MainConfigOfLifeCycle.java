package com.zerocamel.config;

import com.zerocamel.bean.Car;
import com.zerocamel.bean.Cat;
import com.zerocamel.bean.MyBeanPostProcessor;
import org.springframework.context.annotation.*;

/**
 * @program: springannotation
 * @description: 生命周期配置类
 * 1、可以通过@Bean自定义初始化和销毁方法
 * 2、也可以继承InitializingBean和DisposableBean重写初始化和销毁方法
 * 3、JSR250规范
 *      @PostConstruct 在bean创建完成并且属性赋值完成，执行初始化方法
 *      @PreDestroy 在容器销毁bean之前通知我们进行处理
 * 4、BeanPostProcessor,bean的后置处理器
 *    Bean初始化前后
 *    postProcessBeforeInitialization
 *    postProcessAfterInitialization
 * @author: zeroCamel
 * @create: 2020-08-07 09:28
 **/
@ComponentScan(value = "com.zerocamel.bean")
@Configuration
public class MainConfigOfLifeCycle {

    @Scope("prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car()
    {
       return  new Car();
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }
}
