package com.yeahbutstill.mvc.mock;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloGeust() throws Exception {
        mockMvc.perform(get("/helloname"))
                .andExpectAll(
                        status().isOk(),
                        content().string(Matchers.containsString("Hello Guest"))
                );
    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(get("/helloname").queryParam("name", "Dani"))
                .andExpectAll(
                        status().isOk(),
                        content().string(Matchers.containsString("Hello Dani"))
                );
    }

}
