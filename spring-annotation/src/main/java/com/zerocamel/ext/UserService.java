package com.zerocamel.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @program: spring-annotation
 * @description: 使用注解监听多个事件
 * @author: zeroCamel
 * @create: 2020-08-11 13:59
 **/
@Service
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event){
        System.out.println("UserService 监听到的事件"+event);
    }
}
