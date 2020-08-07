import com.zerocamel.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description: Bean生命周期测试类
 * 1、多实例容器不会管理Bean，不会销毁调用销毁方法
 * 2、单实例容器关闭实例自动销毁
 * @author: zeroCamel
 * @create: 2020-08-07 09:33
 **/
public class IOCTestOfLifeCycle {

    @Test
    public void test01()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        out.println("容器创建完成...");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:definitionNames)
        {
            out.println(beanName);
        }
    }

}
