package com.demo.clean.demo.accounting.integration;

import com.demo.clean.accounting.domain.Person;
import com.demo.clean.accounting.domain.PersonEmail;
import com.demo.clean.accounting.infra.persistence.repositories.PersonRepository;
import com.demo.clean.blog.domain.*;
import com.demo.clean.blog.infra.persistence.repositories.CommentRepository;
import com.demo.clean.blog.infra.persistence.repositories.FavoriteRepository;
import com.demo.clean.blog.infra.persistence.repositories.FollowRepository;
import com.demo.clean.blog.infra.persistence.repositories.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDashboardTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    @DisplayName("When user does not exists")
    public void userNotFound() throws Exception {
        var response = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/dashboard/0")).andReturn();
        assertEquals(400, response.getResponse().getStatus());
        assertEquals("{\"message:\" \"User not found\", \"success\": false}", response.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Load user dashboard")
    public void userDashboard() throws Exception {
        this.fixture();
        // Person 1
        var firstPersonResponse = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/dashboard/1")).andReturn().getResponse();
        var mapper = new ObjectMapper();
        var data = mapper.readTree(firstPersonResponse.getContentAsString());
        assertEquals(firstPersonResponse.getStatus(), 200);
        assertEquals(data.get("login").asText(), "rot12");
        assertTrue(data.get("followers").isEmpty());
        var mostRated = mapper.readTree(data.get("most_rated_post").toString());
        assertEquals(mostRated.get("url").asText(), "http://localhost:8080/v1/post/Foo bar1");
        assertEquals(mostRated.get("title").asText(), "Foo bar1");
        var mostRecent = mapper.readTree(data.get("most_recent_post").toString());
        assertEquals(mostRecent.get("url").asText(), "http://localhost:8080/v1/post/Foo bar4");
        assertEquals(mostRecent.get("title").asText(), "Foo bar4");
        assertEquals(data.get("total_posts").asInt(), 2);
        assertEquals(data.get("total_favorites").asInt(), 2);
        assertEquals(data.get("total_followers").asInt(), 0);

        // Person 2
        var secondPersonResponse = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/dashboard/2")).andReturn().getResponse();
        data = mapper.readTree(secondPersonResponse.getContentAsString());
        assertEquals(secondPersonResponse.getStatus(), 200);
        assertEquals(data.get("login").asText(), "rot13");
        assertFalse(data.get("followers").isEmpty());
        mostRated = mapper.readTree(data.get("most_rated_post").toString());
        assertEquals(mostRated.get("url").asText(), "http://localhost:8080/v1/post/Foo bar2");
        assertEquals(mostRated.get("title").asText(), "Foo bar2");
        mostRecent = mapper.readTree(data.get("most_recent_post").toString());
        assertEquals(mostRecent.get("url").asText(), "http://localhost:8080/v1/post/Foo bar3");
        assertEquals(mostRecent.get("title").asText(), "Foo bar3");
        assertEquals(data.get("total_posts").asInt(), 2);
        assertEquals(data.get("total_favorites").asInt(), 1);
        assertEquals(data.get("total_followers").asInt(), 1);
    }

    public void fixture() {
        var person = new Person(PersonEmail.of("foo@bar.com"), "rot12", "root");
        var person2 = new Person(PersonEmail.of("foo@bar.com"), "rot13", "root");
        this.personRepository.save(person);
        this.personRepository.save(person2);
        var post = new Post(PostBody.of("Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit "), PostTitle.of("Foo bar1"), person);
        var post2 = new Post(PostBody.of("Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit "), PostTitle.of("Foo bar2"), person2);
        var post3 = new Post(PostBody.of("Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit "), PostTitle.of("Foo bar3"), person2);
        var post4 = new Post(PostBody.of("Suspendisse eleifend maximus nisi sit amet dapibus. Cras pretium sapien lorem, quis hendrerit elit "), PostTitle.of("Foo bar4"), person);
        this.postRepository.save(post);
        this.postRepository.save(post2);
        this.postRepository.save(post3);
        this.postRepository.save(post4);
        this.favoriteRepository.save(new Favorite(post2, person));
        this.favoriteRepository.save(new Favorite(post3, person));
        this.favoriteRepository.save(new Favorite(post, person2));
        this.commentRepository.save(new Comment(CommentBody.of("Quis hendrerit elit "), person, post2));
        this.followRepository.save(new Follow(person, person2));
    }
}
