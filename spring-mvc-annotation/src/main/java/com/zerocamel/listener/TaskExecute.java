package com.zerocamel.listener;

import com.zerocamel.entity.ResponseMsg;
import com.zerocamel.entity.Task;
import com.zerocamel.service.TaskQueue;
import com.zerocamel.service.TaskService;
import lombok.Synchronized;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @program: spring-mvc-annotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-14 09:35
 **/
@Component
public class TaskExecute {
    private static final Log log =  LogFactory.getLog(TaskService.class);

    private static final Random random = new Random();

    private static final int DEFAULT_STR_LEN = 10;

    private static final String  str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private TaskQueue taskQueue;

    @PostConstruct
    public void init()
    {
        log.info("开始持续处理任务...");

        new Thread(this::execute).start();
    }

    /**
     * 执行业务逻辑处理
     * @throws InterruptedException
     */
    public void execute() {
        while (true)
        {
            Task task;
            synchronized(taskQueue){
                task =taskQueue.take();
            }

            if (task!=null)
            {
                String randomStr = getRandomStr(DEFAULT_STR_LEN);
                ResponseMsg<String> responseMsg = new ResponseMsg<>(0,"success",randomStr);

                log.info("返回结果："+responseMsg);

                task.getTaskResult().setResult(responseMsg);
            }

            int time = random.nextInt(10);
            log.info("处理间隔:"+time);
            try {
                Thread.sleep(time*1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取随机字符串
     * @param len
     * @return
     */
    public String getRandomStr(int len){
        int maxId = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        int ind;
        for(int i =0;i<len;i++)
        {
            int nextInt = random.nextInt(maxId);
            stringBuffer.append(str.charAt(nextInt));
        }

        return String.valueOf(stringBuffer);
    }
}
