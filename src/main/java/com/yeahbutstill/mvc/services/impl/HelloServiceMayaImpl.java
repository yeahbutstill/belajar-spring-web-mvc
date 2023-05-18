package com.yeahbutstill.mvc.services.impl;

import com.yeahbutstill.mvc.services.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceMayaImpl implements HelloService {

    @Override
    public String hello(String name) {

        if (name == null) {
            return "Hello Maya";
        } else {
            return "Hello " + name;
        }

    }

}
