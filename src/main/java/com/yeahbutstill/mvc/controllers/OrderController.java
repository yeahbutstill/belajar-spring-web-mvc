package com.yeahbutstill.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class OrderController {

    @GetMapping(path = "/order/{orderId}/products/{productId}")
    @ResponseBody
    public ResponseEntity<String> order(@PathVariable(name = "orderId") String orderId,
                                        @PathVariable(name = "productId") String productId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("orderId: %s, productId: %s", orderId, productId));
    }

}
