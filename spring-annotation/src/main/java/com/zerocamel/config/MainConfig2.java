package com.zerocamel.config;

import com.zerocamel.bean.Color;
import com.zerocamel.bean.Person;
import com.zerocamel.bean.Red;
import com.zerocamel.condition.LinuxCondition;
import com.zerocamel.condition.MyImportBeanDefinitionRegistrar;
import com.zerocamel.condition.MyImportSelector;
import com.zerocamel.condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * @program: springannotation
 * @description: 测试配置类2
 * 1、指定作用范围
 * 2、ConfigurableBeanFactory#SCOPE_PROTOTYPE 多实例
 *          IOC容器启用不会创建对象放在容器中，每次获取的时候才会调用方法创建
 *   ConfigurableBeanFactory#SCOPE_SINGLETON 单实例
 *          IOC容器启动会调用创建对象方法放在ioc容器中
 *   request 同一个请求创建一个实例
 *   session 同一个session创建一个实例
 *
 * 3、懒加载：
 *          单实例Bean:默认在容器启动的时候创建对象
 *          懒加载：容器启动不创建对象，第一次使用（获取）bean的时候
 * 4、Import id默认是全类名
 *    ImportSelector
 *    ImportBeanDefinitionRegistrar
 *
 * @author: zeroCamel
 * @create: 2020-08-06 14:38
 **/
@Conditional(WindowsCondition.class)
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2
{
//    @Scope("prototype")
    @Lazy
    @Bean
    public Person person()
    {
        System.out.println("容器添加Bean...");
        return  new Person("HH",13);
    }

    /**
     * @Conditional({Condition}) 按照一定条件进行判断，满足条件给容器注册Bean
     * 1、
     */
    @Conditional({WindowsCondition.class})
    @Bean
    public Person bill()
    {
        System.out.println("容器添加Bean...");
        return  new Person("Bill Gates",13);
    }

    @Conditional({LinuxCondition.class})
    @Bean
    public Person linus()
    {
        System.out.println("容器添加Bean...");
        return  new Person("linus",13);
    }
}
