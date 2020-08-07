package com.zerocamel.bean;

import static java.lang.System.out;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-07 09:28
 **/
public class Car {

    public Car() {
        out.println("car construct...");
    }

    public void init()
    {
        out.println("car initial...");
    }
    public void destroy()
    {
        out.println("car destroy...");
    }
}
