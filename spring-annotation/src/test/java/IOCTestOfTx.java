import com.zerocamel.bean.Blue;
import com.zerocamel.bean.Person;
import com.zerocamel.config.MainConfig;
import com.zerocamel.config.MainConfig2;
import com.zerocamel.tx.TxConfig;
import com.zerocamel.tx.UserService;
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
public class IOCTestOfTx {

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        printImport(applicationContext);

        UserService bean = applicationContext.getBean(UserService.class);
        bean.insertUser();
    }

}
