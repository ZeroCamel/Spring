package com.zerocamel.service;

import com.zerocamel.entity.ResponseMsg;
import com.zerocamel.entity.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: spring-servlet
 * @description: ${模拟消费队列}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 23:17
 **/
@Component
public class TaskQueue {
    private static final Log log =  LogFactory.getLog(TaskService.class);

    private static int QUEUE_LENGTH=10;

    private BlockingQueue<Task> queue = new LinkedBlockingQueue<>(QUEUE_LENGTH);

    private int taskId = 0;

    /**
     * 任务绑定消费实体
     * @param deferredResult
     */
    public void put(DeferredResult<ResponseMsg<String>> deferredResult)
    {
        taskId++;
        log.info("任务加入队列，id为："+taskId);
        queue.offer(new Task(taskId,deferredResult));
    }

    /**
     * 获得任务
     * @return
     */
    public Task take()
    {
        Task task = queue.poll();
        log.info("获得任务："+task);

        return task;
    }
}
