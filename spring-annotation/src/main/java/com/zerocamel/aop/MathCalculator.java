package com.zerocamel.aop;

/**
 * @program: Spring
 * @description: ${description}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-09 14:16
 **/
public class MathCalculator {
    public int div(int i,int j)
    {
        System.out.println("计算中...");
        return i/j;
    }
}
