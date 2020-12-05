package com.demo.clean.blog.post.domain;

public class PostBody {

    private String body;
    private int MIN_LENGTH = 10;
    private int MAX_LENGTH = 5000;

    private PostBody(String body) {
        if (body == null || body.length() < MIN_LENGTH || body.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Invalid body");
        }
        this.body = body;
    }

    public static PostBody of(String body) {
        return new PostBody(body);
    }

    @Override
    public String toString() {
        return body;
    }
}
