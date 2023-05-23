package com.yeahbutstill.mvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCurrentPartner() throws Exception {
        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY","yeahbutstill")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("yeahbutstill : Sample Partner"))
        );
    }

    @Test
    void getCurrentPartnerInvalidKey() throws Exception {
        mockMvc.perform(
                get("/partner/current")
        ).andExpectAll(
                status().isInternalServerError(),
                content().string(Matchers.containsString("Unauthorized Exception"))
        );
    }

}