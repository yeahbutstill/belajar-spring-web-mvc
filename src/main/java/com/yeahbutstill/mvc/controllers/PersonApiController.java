package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.models.CreatePersonRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Slf4j
public class PersonApiController {

    @PostMapping(
            path = "/api/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePersonRequest createPerson(@RequestBody @Valid CreatePersonRequest request) {
        log.info("Request: {}", request);
        return request;
    }

}
