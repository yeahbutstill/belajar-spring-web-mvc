package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.services.HelloService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Objects;

@Controller
public class HelloController {

    private final HelloService helloService;

    public HelloController(@Qualifier("helloServiceImpl") HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(path = "/hello")
    public void hello(HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello World!");
    }

    @RequestMapping(path = "/helloname")
    public void helloName(@RequestParam(name = "name", required = false) String name,
                            HttpServletResponse response) throws IOException {

        if (Objects.isNull(name)) {
            name = "Guest";
        }

        response.getWriter().println("Hello " + name);

    }

    @GetMapping(path = "/helloworld")
    public void helloWorld(@RequestParam(name = "name", required = false) String name,
                            HttpServletResponse response) throws IOException {

        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);

    }

}
