import com.zerocamel.config.MainConfigOfLifeCycle;
import com.zerocamel.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description: Bean属性配置类
 * 1、属性赋值 @Value
 * 2、获取外部文件加载属性值@Propertysource
 * 3、获取环境变量里的属性值
 * @author: zeroCamel
 * @create: 2020-08-07 09:33
 **/
public class IOCTestOfPropertity {

    @Test
    public void test01()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        out.println("容器创建完成...");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:definitionNames)
        {
            out.println(beanName);
        }

        Object person = applicationContext.getBean("person");
        out.println(person);

        // 获取环境变量里的Property
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        out.println(property);
    }

}
