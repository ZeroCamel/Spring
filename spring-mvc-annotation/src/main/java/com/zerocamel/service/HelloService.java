package com.zerocamel.service;

import org.springframework.stereotype.Service;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 17:19
 **/
@Service
public class HelloService {

    public String sayHello(String name)
    {
        return "hello "+name;
    }
}
