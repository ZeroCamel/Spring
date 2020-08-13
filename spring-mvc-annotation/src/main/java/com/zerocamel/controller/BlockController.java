package com.zerocamel.controller;

import com.zerocamel.entity.ResponseMsg;
import com.zerocamel.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 22:27
 **/
@Controller
public class BlockController {
    private static final Log log =  LogFactory.getLog(TaskService.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("/getResult")
    public ResponseMsg<String> getResult()
    {
        log.info("接收请求，开始处理...");
        ResponseMsg<String> result = taskService.getResult();
        log.info("接收任务线程并退出...");

        return result;
    }
}
