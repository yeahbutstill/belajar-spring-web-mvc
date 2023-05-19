package com.yeahbutstill.mvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

    @GetMapping(path = "/header/token")
    @ResponseBody
    public ResponseEntity<String> header(@RequestHeader(name = "X-TOKEN") String token) {
        if (token.equals("yeahbutstill")) {
            return ResponseEntity.ok("OK");
        } else if (token.isEmpty() || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("KO");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("KO");
        }
    }

}
