package com.zerocamel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: spring-servlet
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-13 22:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMsg<T> {
    private int code;
    private String message;
    private T data;
}
