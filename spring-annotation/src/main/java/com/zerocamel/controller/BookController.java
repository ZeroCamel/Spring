package com.zerocamel.controller;

import com.zerocamel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @program: springannotation
 * @description:
 * @author: zeroCamel
 * @create: 2020-08-06 13:46
 **/
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
