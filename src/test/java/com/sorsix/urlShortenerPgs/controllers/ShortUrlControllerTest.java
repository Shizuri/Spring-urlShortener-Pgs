package com.sorsix.urlShortenerPgs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sorsix.urlShortenerPgs.services.ShortUrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortUrlController.class)
public class ShortUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShortUrlService service;

    @Test
    public void getAllShots() throws Exception {
        this.mockMvc.perform(get("/api/shorturl/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getMissingShortUrl() throws Exception {
        this.mockMvc.perform(get("/api/shorturl/9999"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBadShortUrlFormat() throws Exception {
        this.mockMvc.perform(get("/api/shorturl/blah"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postRequestIsGood() throws Exception {
        this.mockMvc.perform(post("/api/shorturl/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString("https://www.twitter.com")))
                .andExpect(status().isOk());
    }

    @Test
    public void postRequestEmptyReturns400() throws Exception {
        this.mockMvc.perform(post("/api/shorturl/")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postRequestFormatIsWrong() throws Exception {
        this.mockMvc.perform(post("/api/shorturl/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString("test")))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("Error").value("Invalid URL"));
    }

}