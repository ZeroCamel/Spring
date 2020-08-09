import com.zerocamel.config.MainConfigOfLifeCycle;
import com.zerocamel.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description: 启动Profile环境配置
 * 1、命令行参数指定 -Dspring.profiles.active=test
 * 2、代码的方式激活
 * @author: zeroCamel
 * @create: 2020-08-07 09:33
 **/
public class IOCTestOfProfile {

    @Test
    public void test01()
    {
//        //1、应用上下文有参构造
//
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);


//        //2、设置环境变量的有参构造
        //获取应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //设置环境 可以指定不限数量的参数
        applicationContext.getEnvironment().setDefaultProfiles("dev");
        //注册
        applicationContext.register(MainConfigOfProfile.class);
        //刷新
        applicationContext.refresh();

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:definitionNames)
        {
            out.println(beanName);
        }

    }

}
