package com.zerocamel.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: spring-mvc-annotation
 * @description: 消息队列
 * @author: zeroCamel
 * @create: 2020-08-13 15:26
 **/
public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<DeferredResult<Object>>();

    public static void save(DeferredResult<Object> deferredResult)
    {
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get()
    {
       return queue.poll();
    }

}
