package com.yeahbutstill.mvc.controllers.mock;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "John")
                        .param("middleName", "Doe")
                        .param("lastName", "Sou")
                        .param("email", "J8nQo@example.com")
                        .param("phone", "081234567890")
                        .param("address.street", "Jalan Banyak Lubang")
                        .param("address.city", "Lampung")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12345")
        ).andExpectAll(
                status().isCreated(),
                content().contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE.concat(";charset=UTF-8")),
                content().string(Matchers.containsString("Success create person " +
                        "John Doe Sou with email J8nQo@example.com and phone 081234567890 with address " +
                        "Jalan Banyak Lubang, Lampung, Indonesia, 12345")
                )
        );
    }

}