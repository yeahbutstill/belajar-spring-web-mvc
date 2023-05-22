package com.yeahbutstill.mvc.controllers.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeahbutstill.mvc.models.CreateAddressRequest;
import com.yeahbutstill.mvc.models.CreatePersonRequest;
import com.yeahbutstill.mvc.models.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Dani");
        request.setMiddleName("John");
        request.setLastName("Setiawan");
        request.setAddress(new CreateAddressRequest());
        request.getAddress().setStreet("Jalan 5555");
        request.getAddress().setCity("Bandung");
        request.getAddress().setCountry("Indonesia");
        request.getAddress().setPostalCode("12345");
        request.setHobbies(List.of("Coding", "Gaming", "Music"));
        request.setEmail("dani@yeahbutstill.com");
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "https://www.facebook.com/dani.setiawan"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "https://www.instagram.com/dani.setiawan"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Twitter", "https://www.twitter.com/dani.setiawan"));
        request.setPhone("081234567890");

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isCreated(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE),
                content().json(jsonRequest)
        );
    }

    @Test
    void testCreatePersonValidationError() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName(null);
        request.setMiddleName("John");
        request.setLastName("Setiawan");
        request.setAddress(new CreateAddressRequest());
        request.getAddress().setStreet("Jalan 5555");
        request.getAddress().setCity("Bandung");
        request.getAddress().setCountry("Indonesia");
        request.getAddress().setPostalCode("12345");
        request.setHobbies(List.of("Coding", "Gaming", "Music"));
        request.setEmail(null);
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "https://www.facebook.com/dani.setiawan"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "https://www.instagram.com/dani.setiawan"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Twitter", "https://www.twitter.com/dani.setiawan"));
        request.setPhone(null);

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE),
                content().string(Matchers.containsString("Validation Error"))
        );

    }

}