package com.zerocamel.controller;

import com.zerocamel.service.DeferredResultQueue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @program: spring-mvc-annotation
 * @description: 异步处理-控制器
 * @author: zeroCamel
 * @create: 2020-08-13 14:38
 **/
@RestController
public class AsyncController {

    @RequestMapping("/async01")
    public Callable<String> async01()
    {
        System.out.println("Tomcat主线程开始..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>(){
            public String call() throws Exception {
                System.out.println("副线程开始..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("副线程结束..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
                return "async01";
            }
        };
        System.out.println("Tomcat主线程结束..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
        return callable;
    }

    /**
     * 业务一 创建订单需求
     *
     * 生产者
     *
     * @return
     */
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long)3000,"create fail");

        DeferredResultQueue.save(deferredResult);

        return  deferredResult;
    }

    /**
     * 业务二 创建订单的执行类
     *
     * 消费者
     *
     * 真实的处理逻辑应该是写一个监听器
     * 监听DeferredResultQueue 然后执行Create
     * @return
     */
    @RequestMapping("/create")
    public String create()
    {
        // 创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success>>"+order;
    }

}
