package com.zerocamel.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @program: springannotation
 * @description: 声明式事务
 * @author: zeroCamel
 * @create: 2020-08-10 15:36
 *
 * 环境搭建
 * 1、导入相关依赖
 *    数据源、数据驱动、Spring-jdbc模块
 * 2、配置数据源简化数据库操作JdbcTemplate
 *
 *
 *
 **/
@ComponentScan(value = "com.zerocamel.tx")
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.2.62:3306/jdbc?characterEncoding=utf-8&useSSL=false");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //已经注入再次调用会从容器中寻找已经加载的组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
        return  jdbcTemplate;
    }

}
