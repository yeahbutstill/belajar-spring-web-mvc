package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.services.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Objects;

@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(@Qualifier("helloServiceImpl") HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(path = "/hello")
    public void helloWorld(HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello World!");
    }

    @RequestMapping(path = "/helloname")
    public void helloWorld2(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String name = request.getParameter("name");
        if (Objects.isNull(name)) {
            name = "Guest";
        }

        response.getWriter().println("Hello " + name);

    }

    @RequestMapping(path = "/helloworld")
    public void helloWorld3(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String name = request.getParameter("name");
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);

    }

}
