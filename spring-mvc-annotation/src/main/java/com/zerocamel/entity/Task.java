package com.zerocamel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @program: spring-servlet
 * @description: ${消费实体}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 23:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private int taskId;

    private DeferredResult<ResponseMsg<String>> taskResult;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskResult=" + taskResult +
                '}';
    }
}
