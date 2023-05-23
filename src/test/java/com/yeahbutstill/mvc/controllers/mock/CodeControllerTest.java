package com.yeahbutstill.mvc.controllers.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(
                delete("/product/1")
        ).andExpectAll(
                status().isAccepted()
        );
    }

}