package com.zerocamel.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zerocamel.bean.Grey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @program: Spring
 * @description: ${profile:根据当前环境动态激活和切换一系列组件的功能
 * 1、开发环境 测试环境 生产环境
 * @profile("default") 如果是defalut 默认加载
 * 2、控制配置的颗粒度 可以标记在类上 只有指定可指定类的环境参数一致时 才可加载类里面的配置
 * 3、没有标记的 任何环境下都是加载的
 * }
 * @author: Mr.ZeroCamel
 * @create: 2020-08-08 17:54
 **/
@Profile("test")
@PropertySource("classpath:/dbConfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    private String driverClassName;

    @Value("${db.user}")
    private String user;

    @Bean
    public Grey grey()
    {
        return  new Grey();
    }

    @Profile("test")
    @Bean(value = "testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.124.21:3306/jdbc");
        comboPooledDataSource.setDriverClass(driverClassName);
        return comboPooledDataSource;
    }
    @Profile("dev")
    @Bean(value = "devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.124.21:3306/jpa");
        comboPooledDataSource.setDriverClass(driverClassName);
        return comboPooledDataSource;
    }
    @Profile("prod")
    @Bean(value = "prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}")String pwd) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(pwd);
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.124.21:3306/mybatis");
        comboPooledDataSource.setDriverClass(driverClassName);
        return comboPooledDataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
        this.driverClassName = stringValueResolver.resolveStringValue("${db.driverClass}");

    }
}
