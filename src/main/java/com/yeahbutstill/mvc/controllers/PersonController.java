package com.yeahbutstill.mvc.controllers;

import com.yeahbutstill.mvc.models.CreatePersonRequest;
import com.yeahbutstill.mvc.models.CreateSocialMediaRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@Slf4j
public class PersonController {

    @PostMapping(path = "/person",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    // di banding kita langsung throw exception misalnya, saya mau masuk kedalam kondisi, caranya tinggal tambahkan
    // parameter bindingResult. secara otomatis kalau disini datanya tidak valid, maka dia akan otomatis masuk detail dari error nya
    // ke dalam bindingResult, ini hanya untuk validation error saja. untuk exception lain dia tidak akan handle
    public ResponseEntity<String> createPerson(@ModelAttribute @Valid CreatePersonRequest request, BindingResult bindingResult) {
        log.info("Creating person: {}", request);
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        if (!allErrors.isEmpty()) {
            allErrors.forEach(fieldError -> {
                log.error(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You send invalid data : " + allErrors.get(0).getDefaultMessage());
        }

        for (CreateSocialMediaRequest socialMediaRequest : request.getSocialMedias()) {
            log.info(socialMediaRequest.getName() + " : " + socialMediaRequest.getLocation());
        }

        String response = new StringBuilder()
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

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
