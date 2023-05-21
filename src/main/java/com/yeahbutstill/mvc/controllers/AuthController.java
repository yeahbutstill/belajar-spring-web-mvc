package com.yeahbutstill.mvc.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @PostMapping(path = "/auth/login",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<String> login(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "password") String password,
                                        HttpServletResponse servletResponse) {
        if (username.equals("yeahbutstill") && password.equals("janjok")) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(3600); // 1 hour
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body("Login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @GetMapping(path = "/auth/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    // parameter ini akan di ambil dari cookie
    public ResponseEntity<String> getUser(@CookieValue(name = "username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + username);
    }

}
