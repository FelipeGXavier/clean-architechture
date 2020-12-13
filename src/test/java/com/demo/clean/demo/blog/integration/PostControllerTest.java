package com.demo.clean.demo.blog.integration;

import com.demo.clean.accounting.domain.Person;
import com.demo.clean.accounting.domain.PersonEmail;
import com.demo.clean.accounting.infra.persistence.repositories.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;


    @BeforeAll
    public void setUp() {
        var person = new Person(PersonEmail.of("foo@bar.com"), "rot12", "root");
        this.personRepository.save(person);
    }

    @Test
    @DisplayName("Create valid post for user")
    public void validPost() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"Lorem ipsum id est der!\",\n" +
                        "    \"body\": \"Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit semper quis. Nullam non quam ligula. Cras ultrices lorem vel mauris \",\n" +
                        "    \"user\": 1,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 201);
    }

    @Test
    @DisplayName("Create post for invalid user")
    public void invalidUser() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"Lorem ipsum id est der!\",\n" +
                        "    \"body\": \"Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit semper quis. Nullam non quam ligula. Cras ultrices lorem vel mauris \",\n" +
                        "    \"user\": 0,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 400);
        assertEquals(response.getResponse().getContentAsString(), "{\"message:\" \"User not found\", \"success\": false}");
    }

    @Test
    @DisplayName("Post without minimum length of title")
    public void postWithoutMinLengthTitle() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"\",\n" +
                        "    \"body\": \"Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit semper quis. Nullam non quam ligula. Cras ultrices lorem vel mauris \",\n" +
                        "    \"user\": 1,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 400);
        assertEquals(response.getResponse().getContentAsString(), "{\"message:\" \"Invalid title\", \"success\": false}");
    }

    @DisplayName("Post without minimum length of body")
    public void postWithoutMinimumLengthBody() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"Lorem ipsum id est der!\",\n" +
                        "    \"body\": \"\",\n" +
                        "    \"user\": 1,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 400);
        assertEquals(response.getResponse().getContentAsString(), "{\"message:\" \"Invalid body\", \"success\": false}");
    }

    @Test
    @DisplayName("Post exceeding max body length")
    public void postExceedBodyMaxLength() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"Lorem ipsum id est der!\",\n" +
                        "    \"body\": \"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\",\n" +
                        "    \"user\": 1,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 400);
        assertEquals(response.getResponse().getContentAsString(), "{\"message:\" \"Invalid body\", \"success\": false}");
    }

    @DisplayName("Post exceed maximum length of body")
    public void postExceedTitleMaxLength() throws Exception {
        var response = this.mockMvc
                .perform(post("/v1/post").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"title\": \"Lorem ipsum id est der!\",\n" +
                        "    \"body\": \"IrOuILxOhasVxO7aLsQ1qf2NpcolL5avKgrqxbOaSLwrXhYtwza\",\n" +
                        "    \"user\": 1,\n" +
                        "    \"links\": []\n" +
                        "}"))
                .andDo(print())
                .andReturn();
        assertEquals(response.getResponse().getStatus(), 400);
        assertEquals(response.getResponse().getContentAsString(), "{\"message:\" \"Invalid title\", \"success\": false}");
    }


}
