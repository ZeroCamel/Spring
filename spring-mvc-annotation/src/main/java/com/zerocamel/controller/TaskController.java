package com.zerocamel.controller;

import com.zerocamel.entity.ResponseMsg;
import com.zerocamel.entity.TaskSet;
import com.zerocamel.service.TaskQueue;
import com.zerocamel.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 22:59
 **/
@Controller
public class TaskController {

    private static final Log log =  LogFactory.getLog(TaskService.class);

    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1,"超时","out of time");

    private static final long OUT_OF_TIME =3000L;

    @Autowired
    private TaskQueue taskQueue;

    @Autowired
    private TaskSet taskSet;

    @Autowired
    private TaskService taskService;

    /**
     * 场景一
     * @return
     */
    @GetMapping("/getAsyncResult")
    public Callable<ResponseMsg<String>> getResult()
    {
        log.info("接收请求，开始处理...");

        //Callable<ResponseMsg<String>> callable = (()-> {
        //return taskService.getResult();
        //});

        Callable<ResponseMsg<String>> callable = new Callable<ResponseMsg<String>>() {

            public ResponseMsg<String> call() throws Exception {
                return  taskService.getResult();
            }
        };

        log.info("接收任务线程并退出...");

        return callable;
    }

    /**
     *场景二
     * @return
     */
    @GetMapping("/getAsyncResult01")
    public DeferredResult<ResponseMsg<String >> getResult01()
    {
        log.info("接收消息,开始处理...");
        DeferredResult<ResponseMsg<String>> deferredResult = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);

        deferredResult.onTimeout(()->{
            log.info("调用超时...");
        });

        deferredResult.onCompletion(()->{
            log.info("调用完成...");
        });

        synchronized (taskQueue){
            taskQueue.put(deferredResult);
        }

        log.info("接收任务线程并完成退出");

        return deferredResult;
    }


    /**
     * 场景三
     * @return
     */
    @GetMapping("/getAsyncResult02")
    public DeferredResult<ResponseMsg<String >> getResult02()
    {
        log.info("接收消息,开始处理...");
        DeferredResult<ResponseMsg<String>> deferredResult = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);

        deferredResult.onTimeout(()->{
            log.info("调用超时...移除任务，此时队列长度为："+ taskSet.getSet().size());

            synchronized (taskSet.getSet())
            {
                taskSet.getSet().remove(deferredResult);
            }

        });

        deferredResult.onCompletion(()->{
            log.info("调用完成...移除任务，此时队列长度为:"+taskSet.getSet().size());

            synchronized (taskSet.getSet())
            {
                taskSet.getSet().remove(deferredResult);
            }

        });

        synchronized (taskQueue){
            taskSet.getSet().add(deferredResult);
        }

        log.info("接收任务线程并完成退出");

        return deferredResult;
    }


}
