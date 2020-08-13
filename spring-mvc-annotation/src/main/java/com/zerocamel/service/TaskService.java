package com.zerocamel.service;

import com.zerocamel.entity.ResponseMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @program: spring-servlet
 * @description: ${任务服务
 * TODO:
 * 1、阻塞调用
 * 2、Callable调用实际结果处理}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 22:11
 **/
@Service
public class TaskService {
    private static final Log log =  LogFactory.getLog(TaskService.class);

    public ResponseMsg<String> getResult()
    {
        log.info("任务开始执行，持续等待中...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("任务处理完成...");

        return new ResponseMsg<String>(0,"操作成功!","success");

    }
}
