package com.zerocamel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-servlet
 * @description: ${三大组件之-Servlet}
 * @author: Mr.ZeroCamel
 * @create: 2020-08-12 11:59
 **/
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("zhangsan");
    }
}
