package com.zerocamel.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: spring-mvc-annotation
 * @description: HashMap Hashtale HashSet 简单区别
 * https://www.cnblogs.com/steven520213/p/8192091.html
 * @author: zeroCamel
 * @create: 2020-08-14 10:43
 **/
@Data
@Component
public class TaskSet {
    private Set<DeferredResult<ResponseMsg<String>>> set = new HashSet<>();
}
