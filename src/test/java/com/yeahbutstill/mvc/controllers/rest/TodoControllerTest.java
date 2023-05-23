package com.yeahbutstill.mvc.controllers.rest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addTodoInvalid() throws Exception {
        mockMvc.perform(
                post("/todos")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        );
    }

    @Test
    void addTodo() throws Exception {
        mockMvc.perform(
                post("/todos")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("todo", "yeahbutstill")
        ).andExpectAll(
                status().isCreated(),
                content().string(Matchers.containsString("yeahbutstill")),
                content().contentType(MediaType.APPLICATION_JSON_VALUE)
        );
    }

    @Test
    void getTodos() throws Exception {
        mockMvc.perform(
                get("/todos")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE),
                content().string(Matchers.containsString("yeahbutstill"))
        );
    }

}