package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserController {

    @GetMapping(path = "/user/current")
    @ResponseBody
    public String getCurrentUser(@SessionAttribute(name = "user") User user) {
        return "Hello " + user.username();
    }

}
