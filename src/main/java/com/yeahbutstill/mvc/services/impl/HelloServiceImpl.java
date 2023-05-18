package com.yeahbutstill.mvc.services.impl;

import com.yeahbutstill.mvc.services.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {

        if (name == null) {
            return "Hello Guest";
        } else {
            return "Hello " + name;
        }

    }

}
