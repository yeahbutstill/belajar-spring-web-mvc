package com.yeahbutstill.mvc.controllers.mock;

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
class HeaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHeaderOk() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .contentType(MediaType.TEXT_PLAIN)
                        .header("X-TOKEN", "yeahbutstill")
        ).andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_PLAIN_VALUE)),
                content().string(Matchers.containsString("OK")),
                content().contentType("text/plain;charset=UTF-8")
        );
    }

    @Test
    void testHeaderKo() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .contentType(MediaType.TEXT_PLAIN)
                        .header("X-TOKEN", "hahahaha")
        ).andExpectAll(
                status().isBadRequest(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_PLAIN_VALUE)),
                content().string(Matchers.containsString("KO")),
                content().contentType("text/plain;charset=UTF-8")
        );
    }


}