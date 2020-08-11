import com.zerocamel.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.lang.System.out;

/**
 * @program: spring-annotation
 * @description: 测试类
 * @author: zeroCamel
 * @create: 2020-08-06 13:50
 **/
public class IOCTestOfExt {

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        printImport(applicationContext);

        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
        });

    }

}
