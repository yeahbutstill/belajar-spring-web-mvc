package com.yeahbutstill.mvc.controllers.mock;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                        .param("hobbies[0]", "Reading")
                        .param("hobbies[1]", "Coding")
                        .param("hobbies[2]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "https://www.facebook.com/john.doe")
                        .param("socialMedias[1].name", "Twitter")
                        .param("socialMedias[1].location", "https://www.twitter.com/john.doe")
                        .param("socialMedias[2].name", "Instagram")
                        .param("socialMedias[2].location", "https://www.instagram.com/john.doe")
        ).andExpectAll(
                status().isCreated(),
                content().contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE.concat(";charset=UTF-8")),
                content().string(Matchers.containsString("Success create person " +
                        "John Doe Sou with email J8nQo@example.com, and " +
                        "phone 081234567890, with " +
                        "address Jalan Banyak Lubang, Lampung, Indonesia, 12345. " +
                        "Hobbies: Reading, Coding, Gaming, " +
                        "Social Medias: Facebook, https://www.facebook.com/john.doe, " +
                        "Twitter, https://www.twitter.com/john.doe, " +
                        "Instagram, https://www.instagram.com/john.doe")
                )
        );
    }

    @Test
    void testCreatePersonInvalid() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Doe")
                        .param("lastName", "Sou")
                        .param("address.street", "Jalan Banyak Lubang")
                        .param("address.city", "Lampung")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12345")
                        .param("hobbies[0]", "Reading")
                        .param("hobbies[1]", "Coding")
                        .param("hobbies[2]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "https://www.facebook.com/john.doe")
                        .param("socialMedias[1].name", "Twitter")
                        .param("socialMedias[1].location", "https://www.twitter.com/john.doe")
                        .param("socialMedias[2].name", "Instagram")
                        .param("socialMedias[2].location", "https://www.instagram.com/john.doe")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }

}