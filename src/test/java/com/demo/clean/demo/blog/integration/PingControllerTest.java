package com.demo.clean.demo.blog.integration;

import com.demo.clean.blog.infra.http.PingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {PingController.class})
@WebMvcTest
public class PingControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
        var response = this.mockMvc.perform(get("/v1/ping")).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(response.getResponse().getContentAsString(), "Pong");
    }

}
