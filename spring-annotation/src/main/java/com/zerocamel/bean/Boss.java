package com.zerocamel.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Spring
 * @description: ${测试Autowired的标记位置}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-08 15:34
 **/
@Component
public class Boss {
    private Car car;

    public Car getCar() {
        return car;
    }
    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
