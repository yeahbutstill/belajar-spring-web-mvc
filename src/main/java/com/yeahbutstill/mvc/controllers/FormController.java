package com.yeahbutstill.mvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormController {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @PostMapping(
            path = "/form/hello",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name) {

        return """
                <html>
                    <body>
                        <h1>Hello, $name</h1>
                    </body>
                </html>
                """.replace("$name", name);

    }

    @PostMapping(
            path = "/form/person",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public ResponseEntity<String> createPerson(@RequestParam(name = "name") String name,
                                               @RequestParam(name = "birthDate") Date birthDate,
                                               @RequestParam(name = "address") String address) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dateFormat.format(birthDate) + " " + name + " " + address);
    }

}
