package com.zerocamel.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: spring-servlet
 * @description: Servlet3.0 异步处理
 * 1、asyncSupport 开启异步支持
 * 2、startAsync) 开启异步
 * 2、req.getAsyncContext();
 * @author: zeroCamel
 * @create: 2020-08-13 11:16
 **/
@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Tomcat主线程开始..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
        // 获取异步上下文
        AsyncContext asyncContext = req.startAsync();
        // 设置超时时间等于异步处理时间
        asyncContext.setTimeout(3000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("异步处理副线程开始..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
                    sayHello();

                    // 获取异步上下文
                    AsyncContext context = req.getAsyncContext();
                    ServletResponse response = context.getResponse();

                    try {
                        response.getWriter().write("hello async");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    asyncContext.complete();
                    System.out.println("异步处理副线程结束..."+Thread.currentThread()+":时间"+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Tomcat主线程结束...."+Thread.currentThread()+":时间"+System.currentTimeMillis());
    }

    /**
     * 耗时的处理
     * @throws InterruptedException
     */
    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread()+" processing...");
        Thread.sleep(3000);
    }
}
