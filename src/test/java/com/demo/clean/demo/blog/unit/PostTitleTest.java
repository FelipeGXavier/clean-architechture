package com.demo.clean.demo.blog.unit;

import com.demo.clean.blog.domain.PostBody;
import com.demo.clean.blog.domain.PostTitle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PostTitleTest {

    @Test
    @DisplayName("Valid title for post")
    public void validBody() {
        var body = "Lorem ipsum dolor sit";
        PostTitle postTitle = PostTitle.of(body);
        assertEquals(postTitle.toString().length(), body.length());
    }

    @Test
    @DisplayName("Null title")
    public void nullBody() {
        String body = null;
        var exception = assertThrows(IllegalArgumentException.class, () -> PostTitle.of(body));
        assertEquals(exception.getMessage(), "Invalid title");
    }

    @Test
    @DisplayName("Title without minimum length required")
    public void withoutMinLength() {
        String body = "";
        var exception = assertThrows(IllegalArgumentException.class, () -> PostTitle.of(body));
        assertEquals(exception.getMessage(), "Invalid title");
    }

    @Test
    @DisplayName("Title exceeding maximum length")
    public void exceedMaxLength() {
        String body = "IrOuILxOhasVxO7aLsQ1qf2NpcolL5avKgrqxbOaSLwrXhYtwza";
        var exception = assertThrows(IllegalArgumentException.class, () -> PostTitle.of(body));
        assertEquals(exception.getMessage(), "Invalid title");
    }
}
