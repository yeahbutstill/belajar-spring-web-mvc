package com.yeahbutstill.mvc.mock;

import com.yeahbutstill.mvc.services.HelloService;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Qualifier("helloServiceImpl")
    @MockBean
    private HelloService helloService;

    @BeforeEach
    void setup() {
        Mockito.when(helloService.hello( Mockito.anyString()))
                .thenReturn("Hello Guys");
    }

    @Test
    void helloGeust1() throws Exception {
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

    @Test
    void testMock() throws Exception {
        mockMvc.perform(get("/helloworld")
                .queryParam("name", "Budi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guys"))
        );
    }

    @Test
    void testNotAllowedPost() throws Exception {
        mockMvc.perform(post("/helloworld")
                .queryParam("name", "Budi")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );
    }

}
