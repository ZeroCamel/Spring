package com.zerocamel.controller;

import com.zerocamel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 17:17
 **/

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello()
    {
        String tomcat = helloService.sayHello("tomcat");
        return tomcat;
    }

    @RequestMapping("/suc")
    public String success()
    {
        return "success";
    }
}
