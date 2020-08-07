import com.zerocamel.config.MainConfigOfAutowired;
import com.zerocamel.config.MainConfigOfPropertyValues;
import com.zerocamel.service.BookService;
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
public class IOCTestOfAutowired {

    @Test
    public void test01()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        out.println("容器创建完成...");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:definitionNames)
        {
            out.println(beanName);
        }

        Object object = applicationContext.getBean(BookService.class);
        out.println(object);
    }

}
