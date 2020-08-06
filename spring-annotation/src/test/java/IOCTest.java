import com.zerocamel.bean.Blue;
import com.zerocamel.bean.Person;
import com.zerocamel.config.MainConfig;
import com.zerocamel.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description: 测试类
 * @author: zeroCamel
 * @create: 2020-08-06 13:50
 **/
public class IOCTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    private void printImport(ApplicationContext applicationContext)
    {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanDefinitionNames)
        {
            out.println(beanName);
        }
    }

    @SuppressWarnings("resource")
    @Test
    public void test01()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        printImport(applicationContext);
    }

    /**
     * 测试注入的单例模式/懒加载/作用域（request session 启动前 启动后）
     */
    @SuppressWarnings("resource")
    @Test
    public void test02()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        printImport(applicationContext);

        Object person = applicationContext.getBean(Person.class);
        Object person1 = applicationContext.getBean(Person.class);

        out.println(person == person1);
    }

    /**
     * 测试Conditional条件加载
     * 1、根据操作系统注入响应的Bean
     * 2、可以标注在方法或者类上
     * 3、增加VM options命令行参数为-Dos.name='linux'
     */
    @SuppressWarnings("resource")
    @Test
    public void test03()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        Environment environment = applicationContext.getEnvironment();
        // 动态获取操作系统的值
        String property = environment.getProperty("os.name");
        out.println(property);

        printImport(applicationContext);

        Map<String,Person> namesForType = applicationContext.getBeansOfType(Person.class);
        out.println(namesForType);

    }

    @Test
    public void testIml()
    {
        printImport(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        out.println(bean);
    }

}
