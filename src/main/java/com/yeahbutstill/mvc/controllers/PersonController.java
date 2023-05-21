package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.models.CreatePersonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Slf4j
public class PersonController {

    @PostMapping(path = "/person",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String createPerson(@ModelAttribute CreatePersonRequest request) {
        log.info("Creating person: {}", request);
        return new StringBuilder()
                .append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName()).append(" ")
                .append("with email ").append(request.getEmail()).append(", ")
                .append("and phone ").append(request.getPhone()).append(", ")
                .append("with address ")
                .append(request.getAddress().getStreet()).append(", ")
                .append(request.getAddress().getCity()).append(", ")
                .append(request.getAddress().getCountry()).append(", ")
                .append(request.getAddress().getPostalCode())
                .append(". ")
                .append("Hobbies: ")
                .append(request.getHobbies().get(0)).append(", ")
                .append(request.getHobbies().get(1)).append(", ")
                .append(request.getHobbies().get(2)).append(", ")
                .append("Social Medias: ")
                .append(request.getSocialMedias().get(0).getName()).append(", ")
                .append(request.getSocialMedias().get(0).getLocation()).append(", ")
                .append(request.getSocialMedias().get(1).getName()).append(", ")
                .append(request.getSocialMedias().get(1).getLocation()).append(", ")
                .append(request.getSocialMedias().get(2).getName()).append(", ")
                .append(request.getSocialMedias().get(2).getLocation())
                .toString();
    }

}
