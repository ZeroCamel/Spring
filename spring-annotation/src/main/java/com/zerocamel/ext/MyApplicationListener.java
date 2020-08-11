package com.zerocamel.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: springannotation
 * @description: 事件监听器
 * @author: zeroCamel
 * @create: 2020-08-11 12:47
 *
 * 1、当容器中发布此事件以后，方法触发
 *
 **/
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件:"+event);
    }
}
