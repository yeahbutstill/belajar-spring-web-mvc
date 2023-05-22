package com.yeahbutstill.mvc.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController { // disini wajib implement ErrorController,
    // kalau tidak implement ErrorController, dia otomatis tidak ditampilkan sebagai default error

    @RequestMapping(path = "/error") // disini wajib pathnya harus /error
    public ResponseEntity<String> error(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        // ini bisa aja null
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Error Page</title>
                </head>
                <body>
                    <h1>Error Page</h1>
                    <p>
                        <strong>Status:</strong>
                        <span>${status}</span>
                    </p>
                    <p>
                        <strong>Message:</strong>
                        <span>${message}</span>
                    </p>
                </body>
                </html>
                """.replace("${status}", status.toString())
                .replace("${message}", message);

        return ResponseEntity.status(status).body(html);
    }

}
