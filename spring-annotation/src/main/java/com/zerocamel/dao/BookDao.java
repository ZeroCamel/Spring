package com.zerocamel.dao;

import org.springframework.stereotype.Repository;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-06 13:48
 **/
@Repository
public class BookDao {
    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
