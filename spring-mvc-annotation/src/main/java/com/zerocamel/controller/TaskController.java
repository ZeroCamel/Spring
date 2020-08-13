package com.zerocamel.controller;

import com.zerocamel.entity.ResponseMsg;
import com.zerocamel.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Autowired
    private TaskService taskService;

    @GetMapping("/getAsyncResult")
    public Callable<ResponseMsg<String>> getResult()
    {
        log.info("接收请求，开始处理...");

//        Callable<ResponseMsg<String>> callable = (()-> {
//            return taskService.getResult();
//        });

        Callable<ResponseMsg<String>> callable = new Callable<ResponseMsg<String>>() {
            @Override
            public ResponseMsg<String> call() throws Exception {
                return  taskService.getResult();
            }
        };

        log.info("接收任务线程并退出...");

        return callable;
    }
}
