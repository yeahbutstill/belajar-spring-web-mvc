package com.yeahbutstill.mvc.controllers.mock;

import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginSuccess() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("username", "yeahbutstill")
                        .param("password", "janjok")
        ).andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)),
                content().string(Matchers.containsString("Login success")),
                content().contentType("application/json"),
                cookie().value("username", Matchers.containsString("yeahbutstill"))
        );
    }

    @Test
    void testLoginFailed() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("username", "yeahbutstill")
                        .param("password", "rahasia")
        ).andExpectAll(
                status().isUnauthorized(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)),
                content().string(Matchers.containsString("Login failed")),
                content().contentType("application/json")
        );
    }

    @Test
    void testGetUser() throws Exception {
        mockMvc.perform(
                get("/auth/user")
                        /*
                         * karena cookie itu sebenarnya dia otomatis dikirim oleh browser, makanya kalau di unit test
                         * mau engga mau kita harus kirimkan secara manual
                         */
                        .cookie(new Cookie("username", "yeahbutstill"))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)),
                content().string(Matchers.containsString("Hello yeahbutstill")),
                content().contentType("application/json")
        );
    }


}