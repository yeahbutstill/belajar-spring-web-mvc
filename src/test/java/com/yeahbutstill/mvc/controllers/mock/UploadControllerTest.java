package com.yeahbutstill.mvc.controllers.mock;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUpload() throws Exception {
        mockMvc.perform(
                multipart("/upload/profile")
                        .file(new MockMultipartFile("multipartFile",
                                "profile1.png", "image/png",
                                getClass().getResourceAsStream("/images/profile1.png")))
                        .param("name", "yeahbutstill")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success save profile yeahbutstill to upload/profile1.png"))
        );
    }

}