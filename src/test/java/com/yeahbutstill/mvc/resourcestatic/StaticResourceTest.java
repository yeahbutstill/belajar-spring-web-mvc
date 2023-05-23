package com.yeahbutstill.mvc.resourcestatic;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StaticResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStaticResource() throws Exception {
        mockMvc.perform(
                get("/index.html")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello World"))
        );
    }

}
