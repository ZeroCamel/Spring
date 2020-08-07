package com.zerocamel.service;

import com.zerocamel.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-06 13:47
 **/
@Service
public class BookService {

    //明确指定装配的BeanId
    //@Qualifier("bookDao")
    //@Resource(name="bookDao2)
    @Autowired(required = false)
    public BookDao bookDao2;

    public void print()
    {
        System.out.println(bookDao2);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao2+
                '}';
    }
}
